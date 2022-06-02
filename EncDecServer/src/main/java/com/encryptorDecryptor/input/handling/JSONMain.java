package com.encryptorDecryptor.input.handling;

import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class JSONMain {
	
	public static void main(String[] args) {
		File jsonFile = new File("C:\\Users\\shach\\eclipse-workspace\\EncryptionProject\\src\\main\\resources\\testFile.json");
		JSONReading json;
		EncryptionEventObserver eeo = new EncryptionEventObserver();
		
		//new File("C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\key.txt").delete();
		try {
			json = new JSONReading(jsonFile);
			json.addObserver(eeo);
			json.handleEncrypt();
			json.handleDecrypt();
		} catch (InvalidPathException | SecurityException | IOException | InvalidEncryptionAlgorithmTypeException
				| InterruptedException e) {
			e.printStackTrace();
		}
	}
}
