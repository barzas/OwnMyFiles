package encryptor;
import java.io.*;
import java.util.*;


public class Encryptor {
	//all constants
	static final String DEFAULT = "default";
	static final String ENCRYPTION = "0";
	static final String DECRYPTION = "1"; 
	static final int STARTS_AT = 32;
	static final int ASCII_7_BITS = 127;
	static final int GRAPHIC_ASCII = ASCII_7_BITS - STARTS_AT;
	
	private static boolean DecOrEnc(String userChoice) { //returns true if the input is either decryption or
													  //encryption, false if neither
		return (userChoice.equals(ENCRYPTION) || userChoice.equals(DECRYPTION));
	}
	
	public static void handleInput(String userChoice) { //handles user input and calls the requested function
		while(!DecOrEnc(userChoice)) {
			userChoice = reader.nextLine();
			if(!DecOrEnc(userChoice)) System.out.println("please type a valid input");
		}
		if(userChoice.equals(ENCRYPTION)) encryptionChoice();
		else decryptionChoice();
		
	}

	
	private static void readAndWriteEnc(String path, String newFilePath) { //writes the encrypted file and the key file of
																			//the original file
		int key = getKey();
		try {
			File myFile = new File(newFilePath); 
			File userFile = new File(path);
			String keyPath = path.substring(0, path.lastIndexOf("\\") + 1) + "key.txt";
			File keyFile = new File(keyPath);
			myFile.createNewFile();
			keyFile.createNewFile();
			FileWriter myWriter = new FileWriter(myFile);
		    FileWriter keyWriter = new FileWriter(keyFile);
		    keyWriter.write(String.valueOf(key));
		    keyWriter.close();
			try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       myWriter.write(convertStringEnc(line, key) + "\n");
			    }
			}
			
			myWriter.close();
			
			System.out.printf("encrypted file location: %s, key file location: %s \n", newFilePath, keyPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void encryptionChoice() { //handles encryption choice by the user
		
		System.out.println("please type the path to your file");
		String path = reader.nextLine();
		String[] splitPath = path.split("\\.");
		String pathHead = splitPath[0];
		String pathExtention = splitPath[splitPath.length - 1];
		String newPath = pathHead + "_encrypted." + pathExtention;
		readAndWriteEnc(path, newPath);
		
		
	}
	
	
	private static void readAndWriteDec(String filePath, String keyPath) { //writes the decrypted file using the key file
																			//and the encrypted file
		int key = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(keyPath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       key = Integer.parseInt(line);
		    }
		}
		catch(Exception e) {
			e.printStackTrace();		}
		
		try {
			String[] splitPath = filePath.split("\\.");
			String pathExtention = splitPath[splitPath.length - 1];
			
			String newPath = filePath.substring(0, filePath.lastIndexOf("_")) + "_decrypted." + pathExtention; 
			File decFile = new File(newPath);
			decFile.createNewFile();
			FileWriter myWriter = new FileWriter(decFile);
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       myWriter.write(convertStringDec(line, key) + "\n");
			    }
			}
			myWriter.close();
		}
		catch(Exception e) {
			e.printStackTrace();		}
		

		
	}
	
	private static void decryptionChoice() { //handles decryption choice by the user
		System.out.println("please type the path to your encrypted file");
		String encPath = reader.nextLine();
		System.out.println("please type the path to your key file");
		String keyPath = reader.nextLine();
		readAndWriteDec(encPath, keyPath);
		
	}
	
	private static int getKey() { //returns the key of the encryption
		Random rand = new Random();
		int randNum = rand.nextInt(Integer.MAX_VALUE) % GRAPHIC_ASCII;
		return randNum;
	}
	
	private static String convertStringEnc(String str, int key) {
		StringBuilder sb = new StringBuilder(); 
		for(int i = 0; i < str.length(); i++){ 
			sb.append((char)(STARTS_AT + (str.charAt(i) + key) % GRAPHIC_ASCII)); 
		} 
		return sb.toString();
	}

	private static String convertStringDec(String str, int key) {
		StringBuilder sb = new StringBuilder(); 
		for(int i = 0; i < str.length(); i++){ 
			sb.append((char)(Math.floorMod((int)(str.charAt(i) - key - STARTS_AT + GRAPHIC_ASCII), GRAPHIC_ASCII + STARTS_AT))); 
		} 
		return sb.toString();
	}
	public static Scanner reader = new Scanner(System.in);
}
