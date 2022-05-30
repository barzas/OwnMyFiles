package file.handling;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

import algorithm.handling.DoubleEncryption;
import algorithm.handling.RepeatEncryption;
import algorithm.handling.ShiftMultiplyEncryption;
import algorithm.handling.ShiftUpEncryption;
import algorithm.handling.XorEncryption;
import encryption.logs.EncryptionEventObserver;
import multithreading.AsyncDirectoryProcessor;
import multithreading.SyncDirectoryProcessor;

public class FileEncryptorMain {
	//if you test this, make sure to change the following paths in accordance to your own PC!
		static String ORIG_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester.txt";
		static String KEY_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\key.txt";
		static String OUTPUT_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester_encrypted.txt";
		static String DECRYPT_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester_decrypted.txt";
		static String DIRECTORY_PATH = "C:\\Users\\shach\\eclipse-workspace\\EncryptionProject\\dirTesting";
		
	public static Scanner reader = new Scanner(System.in);	
	public static void main(String[] args) throws InvalidPathException, IOException {
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
		ShiftUpEncryption sue = new ShiftUpEncryption();
		ShiftMultiplyEncryption sme = new ShiftMultiplyEncryption();
		XorEncryption xe = new XorEncryption();
		DoubleEncryption de = new DoubleEncryption(sme);
		RepeatEncryption re = new RepeatEncryption(sme, 5);
		EncryptionEventObserver eeo = new EncryptionEventObserver();
		EncryptionEventObserver eeo2 = new EncryptionEventObserver();
		AsyncDirectoryProcessor adp = new AsyncDirectoryProcessor(de);
		SyncDirectoryProcessor sdp = new SyncDirectoryProcessor(de);
		sdp.addObserver(eeo2);
		adp.addObserver(eeo);
		
		
		try {
			System.out.println("please enter path to your directory to encrypt");
			String dirPath = reader.nextLine();
			adp.encryptDirectory(dirPath);
			adp.decryptDirectory(dirPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
