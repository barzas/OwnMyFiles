package com.encryptorDecryptor.file.handling;

import com.encryptorDecryptor.algorithm.handling.*;
import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;
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
		/*
		ShiftUpEncryption sue = new ShiftUpEncryption();
		ShiftMultiplyEncryption sme = new ShiftMultiplyEncryption();
		XorEncryption xe = new XorEncryption();
		ArrayList<EncryptionAlgorithmImp> sortByTime = new ArrayList<EncryptionAlgorithmImp>();
		sortByTime.add(sme);
		sortByTime.add(sue);
		sortByTime.add(xe);
		System.out.println("order before sorting: ");
		for(EncryptionAlgorithmImp algorithm : sortByTime) System.out.println(algorithm);
		System.out.println("order after sorting: ");
		Collections.sort(sortByTime, new RunTimeComparator());
		for(EncryptionAlgorithmImp algorithm : sortByTime) System.out.println(algorithm);
		*/
//
//		ShiftUpEncryption shiftUp = new ShiftUpEncryption();
//		ShiftMultiplyEncryption shiftMultiply = new ShiftMultiplyEncryption();
//		XorEncryption xor = new XorEncryption();
//		DoubleEncryption de = new DoubleEncryption(shiftMultiply);
//		RepeatEncryption re = new RepeatEncryption(shiftMultiply, 5);
//
//		try {
//			System.out.println("please enter path to your directory to encrypt");
//			String dirPath = reader.nextLine();
//			IEncryptionAlgorithm encryptAlgo = new ShiftUpEncryption();
//			boolean isAlgoDefined = false;
//			while (!isAlgoDefined) {
//				System.out.println("please enter shiftUp / shiftMultiply / double / xor / repeat");
//				String encryptType = reader.nextLine();
//				switch (encryptType) {
//					case "shiftUp":
//						isAlgoDefined = true;
//						break;
//					case "shiftMultiply":
//						encryptAlgo = new ShiftMultiplyEncryption();
//						isAlgoDefined = true;
//						break;
//					case "double":
//						encryptAlgo = new DoubleEncryption(new ShiftUpEncryption());
//						isAlgoDefined = true;
//						break;
//					case "xor":
//						encryptAlgo = new XorEncryption();
//						isAlgoDefined = true;
//						break;
//					case "repeat":
//						encryptAlgo = new RepeatEncryption(new ShiftUpEncryption(), 5);
//						isAlgoDefined = true;
//						break;
//					default:
//						System.out.println("can't find your chosen algorithm. please try again");
//						break;
//				}
//			}

		UserAction userAction = new UserAction();
		try {
			if (userAction.getAction().equals("encrypt")) {// todo- take from front
				IEncryptionAlgorithm encryptAlgo = userAction.getAlgo(); // todo- take from front
				AsyncDirectoryProcessor asyncDirectoryProcessor = new AsyncDirectoryProcessor(encryptAlgo);
				EncryptionEventObserver asyncObserver = new EncryptionEventObserver();
				asyncDirectoryProcessor.addObserver(asyncObserver);

				asyncDirectoryProcessor.encryptDirectory(userAction.getPath());// todo- take from front
			} else {
				IEncryptionAlgorithm encryptAlgo = userAction.getAlgo(); // todo- take from db
				AsyncDirectoryProcessor asyncDirectoryProcessor = new AsyncDirectoryProcessor(encryptAlgo);
				EncryptionEventObserver asyncObserver = new EncryptionEventObserver();
				asyncDirectoryProcessor.addObserver(asyncObserver);

				asyncDirectoryProcessor.decryptDirectory(userAction.getPath());// todo- take from db
			}
		} catch (IOException | InvalidEncryptionAlgorithmTypeException | InterruptedException e) {
			System.out.println(e);
		}

	}
}
