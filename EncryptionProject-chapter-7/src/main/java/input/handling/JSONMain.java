package input.handling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.google.gson.Gson;

import encryption.logs.EncryptionEventObserver;
import exceptions.InvalidEncryptionAlgorithmTypeException;

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
