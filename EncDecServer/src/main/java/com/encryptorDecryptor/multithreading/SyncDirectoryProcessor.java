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

public class SyncDirectoryProcessor extends DirectoryProcessor{
	private IEncryptionAlgorithm encAlgo;
	private FileEncryptor fileEnc;
	
	public SyncDirectoryProcessor(IEncryptionAlgorithm encAlgo) {
		this.encAlgo = encAlgo;
		//this.fileEnc = new FileEncryptor(encAlgo); //todo - was deleted for not sending key
	}
	
	public SyncDirectoryProcessor(FileEncryptor fileEnc) {
		this.encAlgo = fileEnc.getEncAlgo();
		this.fileEnc = fileEnc;
	}
	
	public void addObserver(EncryptionEventObserver observer) {
		this.fileEnc.addObserver(observer);
	}

	public void encryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException{
		double start = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_ENCRYPTION_STARTED, dirPath, encAlgo, start, getClass());
		Paths.get(dirPath);
		File dir = new File(dirPath);
		File subDir = new File(dir.getAbsoluteFile() + "\\encrypted");
		checkFileErrors(subDir, dir, this.getClass());
		Key key = new Key(dir.getAbsolutePath() + "\\key.txt");
		File keyPath = new File(key.getPath());
		if(keyPath.exists()) keyPath.delete();
//		KeyFileActions.writeKeyFile(key);
		fileEnc.setKeyFileFlag(false);
		for(File file : dir.listFiles()) {
			handleEachFile(file, subDir, OperationEnum.ENCRYPT);
		}
		double end = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_ENCRYPTION_ENDED, dirPath, encAlgo, end - start, this.getClass());
	}
	
	
	public void decryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException{
		double start = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_DECRYPTION_STARTED, dirPath, encAlgo, start, getClass());
		Paths.get(dirPath);
		File dir = new File(dirPath);
		File subDir = new File(dir.getAbsoluteFile() + "\\decrypted");
		checkFileErrors(subDir, dir, this.getClass());
		File encryptedFolder = new File(dirPath + "\\encrypted");
		String keyPath = dir.getAbsolutePath() + "\\key.txt";
		encryptionFilesValidation(encryptedFolder);
		for(File file : encryptedFolder.listFiles()) {
			handleEachFile(file, subDir, OperationEnum.DECRYPT);
		}
		double end = System.currentTimeMillis();
		fileEnc.notifyEvent(EncryptionEventEnum.DIRECTORY_DECRYPTION_ENDED, dirPath, encAlgo, end - start, this.getClass());
	}
	private void handleEachFile(File file, File subDir, OperationEnum operation) throws InvalidPathException, IOException, InvalidEncryptionAlgorithmTypeException  {
		String fileName = file.getName();
		if(!file.isDirectory() && fileName.lastIndexOf('.') != -1) {
			if(fileName.substring(fileName.lastIndexOf('.'), fileName.length()).equals(".txt")
				&& !fileName.equals("key.txt")) {
				switch(operation) {
				case ENCRYPT:
					fileEnc.handleEncryption(file.getPath(), (subDir.getAbsolutePath() + "\\" + file.getName()));
					break;
				
				case DECRYPT:
					fileEnc.handleDecryption(file.getPath(), (subDir.getAbsolutePath() + "\\" + file.getName()));
					break;
				}
			}
		}
	}
}
