package com.encryptorDecryptor.input.handling;

import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class XMLMain {

	public static void main(String[] args) {
		File xmlFile = new File("C:\\Users\\shach\\eclipse-workspace\\EncryptionProject\\src\\main\\resources\\testFile.xml");
		XMLReading xml;
		EncryptionEventObserver eeo = new EncryptionEventObserver();
		try {
			xml = new XMLReading(xmlFile);
			xml.addObserver(eeo);
			new File("C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\key.txt").delete();
			try {
				xml.handleEncrypt();
				xml.handleDecrypt();
			} catch (InvalidPathException | SecurityException | IOException | InvalidEncryptionAlgorithmTypeException
					| InterruptedException e) {
				e.printStackTrace();
			}

		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		
	}

}
