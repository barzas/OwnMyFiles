package com.encryptorDecryptor.file.handling;

import com.encryptorDecryptor.key.Key;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class KeyFileActions {
	/**
	 * @throws IOException
	 * @throws InvalidPathException
	 */
	public static void writeKeyFile(Key key) throws IOException, InvalidPathException { //write a random key to the key filePaths.get(key.getPath());
		File keyFile = new File(key.getPath());
		FileWriter keyWriter = new FileWriter(keyFile, true);
		keyWriter.write(String.valueOf(key.getKey()) + System.lineSeparator());
        keyWriter.close();
	}
	
	/**
	 * @param keyPath
	 * @return
	 * @throws IOException
	 * @throws InvalidPathException
	 */
	public static ArrayList<Integer> readKeyFile(String keyPath) throws IOException, InvalidPathException { //read only the first key from the key file 
        Paths.get(keyPath);
		ArrayList<Integer> keyArr = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(keyPath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	keyArr.add(Integer.parseInt(line));
		    }
			return keyArr;
		}
	}

}
