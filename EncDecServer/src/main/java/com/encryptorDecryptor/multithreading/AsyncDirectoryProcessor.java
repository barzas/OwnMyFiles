package com.encryptorDecryptor.multithreading;

import com.encryptorDecryptor.algorithm.handling.IEncryptionAlgorithm;
import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.enums.EncryptionEventEnum;
import com.encryptorDecryptor.enums.OperationEnum;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;
import com.encryptorDecryptor.file.handling.FileEncryptor;
import com.encryptorDecryptor.file.handling.KeyFileActions;
import com.encryptorDecryptor.key.Key;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncDirectoryProcessor extends DirectoryProcessor{
	private IEncryptionAlgorithm encAlgo;
	private FileEncryptor fileEnc;
	private List<FileThread> threads;
	
	public AsyncDirectoryProcessor(IEncryptionAlgorithm encAlgo) {
		this.encAlgo = encAlgo;
		this.fileEnc = new FileEncryptor(encAlgo);
		this.threads = new ArrayList<>();
	}
	
	public AsyncDirectoryProcessor(FileEncryptor fileEnc) {
		this.encAlgo = fileEnc.getEncAlgo();
		this.fileEnc = fileEnc;
		this.threads = new ArrayList<>();
	}
	
	public void addObserver(EncryptionEventObserver observer) {
		this.fileEnc.addObserver(observer);
	}

	@Override
	public void encryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException, InterruptedException {
		double start = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_ENCRYPTION_STARTED, dirPath, encAlgo, start, this.getClass());
		Paths.get(dirPath);
		File dir = new File(dirPath);
		File subDir = new File(dir.getAbsoluteFile() + "\\encrypted");
		checkFileErrors(subDir, dir, this.getClass());
		Key key = new Key(dir.getAbsolutePath() + "\\key.txt");
		File keyPath = new File(key.getPath());
		if(keyPath.exists()) keyPath.delete();
		KeyFileActions.writeKeyFile(key);
		fileEnc.setKeyFileFlag(true);
		
		for(File file : dir.listFiles()) {
			handleEachFile(file, subDir, key.getPath(), OperationEnum.ENCRYPT);
		}
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.invokeAll(threads); 	
		threadPool.shutdown();
		threads.clear();
		double end = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_ENCRYPTION_ENDED, dirPath, encAlgo, end - start, this.getClass());
	}
	

	@Override
	public void decryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException, InterruptedException {
		double start = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_DECRYPTION_STARTED, dirPath, encAlgo, start, this.getClass());
		Paths.get(dirPath);
		File dir = new File(dirPath);
		File subDir = new File(dir.getAbsoluteFile() + "\\decrypted");
		checkFileErrors(subDir, dir, this.getClass());
		File encryptedFolder = new File(dirPath + "\\encrypted");
		String keyPath = dir.getAbsolutePath() + "\\key.txt";
		encryptionFilesValidation(encryptedFolder, keyPath);
		for(File file : encryptedFolder.listFiles()) {
			handleEachFile(file, subDir, keyPath, OperationEnum.DECRYPT);
		}
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.invokeAll(threads);
		threadPool.shutdown();
		threads.clear();
		double end = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_DECRYPTION_ENDED, dirPath, encAlgo, end - start, this.getClass());	
	}
	
	private void handleEachFile(File file, File subDir, String keyPath, OperationEnum operation)  {
		String fileName = file.getName();
		if(!file.isDirectory() && fileName.lastIndexOf('.') != -1) {
			if(fileName.substring(fileName.lastIndexOf('.'), fileName.length()).equals(".txt")
				&& !fileName.equals("key.txt")) {
				FileThread thread = new FileThread(this.fileEnc, file.getPath(), (subDir.getAbsolutePath() + "\\" + file.getName()), keyPath, operation);
				threads.add(thread);
			}
		}
	}
}
