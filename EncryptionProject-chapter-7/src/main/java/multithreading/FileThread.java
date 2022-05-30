package multithreading;


import java.util.concurrent.Callable;

import enums.OperationEnum;
import file.handling.FileEncryptor;

public class FileThread implements Callable<Integer> {
	private FileEncryptor fileEnc;
	private String filePath;
	private String subDirPath;
	private String keyPath;
	private OperationEnum operation;
	
	public FileThread(FileEncryptor fileEnc, String filePath, String subDirPath, String keyPath, OperationEnum operation) {
		this.fileEnc = fileEnc;
		this.filePath = filePath;
		this.subDirPath = subDirPath;
		this.keyPath = keyPath;
		this.operation = operation;
	}
	/*
	@Override
	public void run() {
		try {
			switch(operation) {
			case ENCRYPT:
				fileEnc.handleEncryption(filePath, subDirPath, keyPath);
				break;
		
			case DECRYPT:
				fileEnc.handleDecryption(filePath, subDirPath, keyPath);
				break;
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public Integer call() throws Exception {
		try {
			switch(operation) {
			case ENCRYPT:
				fileEnc.handleEncryption(filePath, subDirPath, keyPath);
				break;
		
			case DECRYPT:
				fileEnc.handleDecryption(filePath, subDirPath, keyPath);
				break;
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
