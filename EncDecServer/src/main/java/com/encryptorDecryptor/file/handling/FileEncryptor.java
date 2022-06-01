package com.encryptorDecryptor.file.handling;

import com.encryptorDecryptor.algorithm.handling.IEncryptionAlgorithm;
import com.encryptorDecryptor.encryption.logs.EncryptionLog4JLogger;
import com.encryptorDecryptor.enums.EncryptionEventEnum;
import com.encryptorDecryptor.enums.OperationEnum;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;
import com.encryptorDecryptor.key.Key;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Observable;

public class FileEncryptor extends Observable{
	IEncryptionAlgorithm encAlgo;
	private boolean keyFileFlag;
	
	public FileEncryptor(IEncryptionAlgorithm encAlgo) {
		this.encAlgo = encAlgo;
		this.keyFileFlag = false;
	}
	
	public boolean getKeyFileFlag() {
		return keyFileFlag;
	}
	
	public void setKeyFileFlag(boolean keyFileFlag) {
		this.keyFileFlag = keyFileFlag;
	}
	
	public IEncryptionAlgorithm getEncAlgo() {
		return this.encAlgo;
	}
	
	public synchronized void notifyEvent(EncryptionEventEnum event, String origFilePath, IEncryptionAlgorithm encAlgo, String targetFilePath, double time, Class<?> origClass) {
		setChanged();
//		ObserverInfo obInfo = new ObserverInfo(event, origFilePath, encAlgo,targetFilePath, time, origClass);
//		notifyObservers(obInfo);
	}
	
	public synchronized void notifyEvent(EncryptionEventEnum event, String origFilePath, IEncryptionAlgorithm encAlgo, double time, Class<?> origClass) {
		setChanged();
//		ObserverInfo obInfo = new ObserverInfo(event, origFilePath, encAlgo, time, origClass);
		
//		notifyObservers(obInfo);
	}
	
	/**
	 * @param origFilePath
	 * @param encFilePath
	 * @param keyPath
	 * @throws IOException
	 * @throws InvalidPathException
	 * @throws InvalidEncryptionAlgorithmTypeException
	 */
	public void handleEncryption(String origFilePath, String encFilePath, String keyPath) throws IOException, InvalidPathException, InvalidEncryptionAlgorithmTypeException {
		double start = System.currentTimeMillis();
		notifyEvent(EncryptionEventEnum.ENCRYPTION_STARTED, origFilePath, encAlgo, encFilePath, start, this.getClass());
		Paths.get(origFilePath);
        Paths.get(encFilePath);
		Paths.get(keyPath);
		Key key;
		if(!keyFileFlag) {
			key = new Key(keyPath);
			KeyFileActions.writeKeyFile(key);
			keyFileFlag = true;
		}
		else {
			key = new Key(keyPath, KeyFileActions.readKeyFile(keyPath).get(0));
		}
		File encFile = new File(encFilePath); 
		File origFile = new File(origFilePath);
		encFile.createNewFile();	
		writeToFileFromFile(origFile, encFile, key, OperationEnum.ENCRYPT);
		double end = System.currentTimeMillis();
		notifyEvent(EncryptionEventEnum.ENCRYPTION_ENDED, origFilePath, encAlgo, encFilePath, end - start, this.getClass());

	}

	
	/**
	 * @param encFilePath
	 * @param decFilePath
	 * @param keyPath
	 * @throws IOException
	 * @throws InvalidPathException
	 */
	public void handleDecryption(String encFilePath, String decFilePath, String keyPath) throws IOException, InvalidPathException, SecurityException {
		double start = System.currentTimeMillis();
		notifyEvent(EncryptionEventEnum.DECRYPTION_STARTED, encFilePath, encAlgo, decFilePath, start, this.getClass());
		Paths.get(decFilePath);
        Paths.get(encFilePath);
        Paths.get(keyPath);
        Key key = new Key(keyPath, KeyFileActions.readKeyFile(keyPath).get(0));
		File decFile = new File(decFilePath);
		File encFile = new File(encFilePath);
		decFile.createNewFile();
		writeToFileFromFile(encFile, decFile, key, OperationEnum.DECRYPT);
		double end = System.currentTimeMillis();
		notifyEvent(EncryptionEventEnum.DECRYPTION_ENDED, encFilePath, encAlgo, decFilePath, end - start, this.getClass());
	}
	
	private void writeToFileFromFile(File origFile, File targetFile, Key key, OperationEnum operation) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		try(Writer myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile, false), "UTF-8"));
				BufferedReader br = new BufferedReader(new FileReader(origFile))){
			String line = br.readLine();
		    if(line != null) {
		    	switch(operation) {
	    		case ENCRYPT:
	    			myWriter.write(encAlgo.convertStringEnc(line, key));
				    while ((line = br.readLine()) != null) myWriter.write(System.lineSeparator() + encAlgo.convertStringEnc(line, key));
				    break;
	    		case DECRYPT:
	    			myWriter.write(encAlgo.convertStringDec(line, key));
				    while ((line = br.readLine()) != null) myWriter.write(System.lineSeparator() + encAlgo.convertStringDec(line, key));	
				    break;
		    	}
		    }
		    else EncryptionLog4JLogger.warn("The original file read is empty", this.getClass());
		}
	}
}
