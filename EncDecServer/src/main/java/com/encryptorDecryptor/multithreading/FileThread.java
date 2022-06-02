package com.encryptorDecryptor.multithreading;


import com.encryptorDecryptor.enums.OperationEnum;
import com.encryptorDecryptor.file.handling.FileEncryptor;

import java.util.concurrent.Callable;

public class FileThread implements Callable<Integer> {
	private FileEncryptor fileEnc;
	private String filePath;
	private String subDirPath;
	private OperationEnum operation;

	private int key;
	
	public FileThread(FileEncryptor fileEnc, String filePath, String subDirPath, OperationEnum operation, int key) {
		this.fileEnc = fileEnc;
		this.filePath = filePath;
		this.subDirPath = subDirPath;
		this.operation = operation;
		this.key = key;
	}


	@Override
	public Integer call() throws Exception {
		try {
			switch(operation) {
			case ENCRYPT:
				fileEnc.handleEncryption(filePath, subDirPath);
				break;
		
			case DECRYPT:
				fileEnc.handleDecryption(filePath, subDirPath);
				break;
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
