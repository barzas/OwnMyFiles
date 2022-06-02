package com.encryptorDecryptor.file.handling;

import com.encryptorDecryptor.algorithm.handling.*;
import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;
import com.encryptorDecryptor.key.Key;
import com.encryptorDecryptor.multithreading.AsyncDirectoryProcessor;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class FileEncryptorMain {
	//if you test this, make sure to change the following paths in accordance to your own PC!
//		static String ORIG_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester.txt";
//		static String KEY_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\key.txt";
//		static String OUTPUT_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester_encrypted.txt";
//		static String DECRYPT_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester_decrypted.txt";
//		static String DIRECTORY_PATH = "C:\\Users\\shach\\eclipse-workspace\\EncryptionProject\\dirTesting";
//
	public static Scanner reader = new Scanner(System.in);	
	public static void main(String[] args) throws InvalidPathException {
		UserAction userAction = new UserAction();
				try {
					if (userAction.getAction().equals("encrypt")) {
						IEncryptionAlgorithm encryptAlgo = userAction.getAlgoByInput();

						Key key = encryptAlgo.getClass().equals(DoubleEncryption.class) ?
								new Key(Key.generateKey(), Key.generateKey()) :
								new Key(Key.generateKey());

						AsyncDirectoryProcessor asyncDirectoryProcessor = new AsyncDirectoryProcessor(encryptAlgo, key);
						EncryptionEventObserver asyncObserver = new EncryptionEventObserver();
						asyncDirectoryProcessor.addObserver(asyncObserver);

						asyncDirectoryProcessor.encryptDirectory(userAction.getPath());
					} else {
						IEncryptionAlgorithm encryptAlgo = userAction.getAlgoByInput();
						AsyncDirectoryProcessor asyncDirectoryProcessor = new AsyncDirectoryProcessor(encryptAlgo, new Key(193, 173));//change the key while testing
						EncryptionEventObserver asyncObserver = new EncryptionEventObserver();
				asyncDirectoryProcessor.addObserver(asyncObserver);

				asyncDirectoryProcessor.decryptDirectory(userAction.getPath());
			}
		} catch (IOException | InvalidEncryptionAlgorithmTypeException | InterruptedException e) {
			System.out.println(e);
		}

	}
}
