package com.encryptorDecryptor.input.handling;

import com.encryptorDecryptor.key.Key;
import com.google.gson.Gson;
import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.encryption.logs.EncryptionLog4JLogger;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;
import com.encryptorDecryptor.file.handling.FileEncryptor;
import com.encryptorDecryptor.multithreading.AsyncDirectoryProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Map;

public class JSONReading {
	private FileEncryptor fileEnc;
	private File jsonFile;
	
	public JSONReading(File file) throws IOException {
		String filePath = file.getAbsolutePath();
		if(!filePath.substring(filePath.lastIndexOf('.'), filePath.length()).equals(".json"))
			EncryptionLog4JLogger.error("wrong file type given", this.getClass());
		
		ProcessSettings encryptionInfo = readFile(file);
		this.fileEnc = new FileEncryptor(encryptionInfo.getEncAlgo(), Key.generateKey()); // todo-not correct key
		this.jsonFile = file;
	}
	
	public void handleEncrypt() throws IOException, InvalidPathException, InvalidEncryptionAlgorithmTypeException, SecurityException, InterruptedException {
		ProcessSettings encryptionInfo = readFile(jsonFile);
		if(encryptionInfo.getOrigPath().equals("")) 
			EncryptionLog4JLogger.error("no source file path given", this.getClass());
		if(!encryptionInfo.getEncryptPath().equals("") && !encryptionInfo.getDecryptPath().equals("") && !encryptionInfo.getKeyPath().equals("")) {
			fileEnc.handleEncryption(encryptionInfo.getOrigPath(), encryptionInfo.getEncryptPath(), encryptionInfo.getKeyPath());
		}
		else {
			AsyncDirectoryProcessor encryptDir = new AsyncDirectoryProcessor(this.fileEnc);
			encryptDir.encryptDirectory(encryptionInfo.getOrigPath());
		}
	}
	
	public void handleDecrypt() throws IOException, InvalidPathException, InvalidEncryptionAlgorithmTypeException, SecurityException, InterruptedException {
		ProcessSettings encryptionInfo = readFile(jsonFile);
		if(encryptionInfo.getOrigPath().equals("")) 
			EncryptionLog4JLogger.error("no source file path given", this.getClass());
		if(!encryptionInfo.getEncryptPath().equals("") && !encryptionInfo.getDecryptPath().equals("") && !encryptionInfo.getKeyPath().equals("")) {
			fileEnc.handleDecryption(encryptionInfo.getEncryptPath(), encryptionInfo.getDecryptPath(), encryptionInfo.getKeyPath());
		}
		else {
			AsyncDirectoryProcessor decryptDir = new AsyncDirectoryProcessor(this.fileEnc);
			decryptDir.decryptDirectory(encryptionInfo.getOrigPath());
		}
	}
	
	private ProcessSettings readFile(File file) throws IOException {
		Gson gson = new Gson();
		try (BufferedReader br1 = new BufferedReader(new FileReader(file))) {
			Map<?, ?> map = gson.fromJson(br1, Map.class);
	    	ProcessSettings encryptionInfo = new ProcessSettings((String)map.get("Algorithm"), (String)map.get("KeyPath"), (String)map.get("OriginalPath"),
	    			(String)map.get("EncryptionPath"), (String)map.get("DecryptionPath"));
		    return encryptionInfo;
		}
	    
	}
		
	public void addObserver(EncryptionEventObserver observer) {
		this.fileEnc.addObserver(observer);
	}
}
