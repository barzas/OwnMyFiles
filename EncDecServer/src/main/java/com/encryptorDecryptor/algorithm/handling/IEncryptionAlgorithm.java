package com.encryptorDecryptor.algorithm.handling;

import com.encryptorDecryptor.key.Key;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public interface IEncryptionAlgorithm {
	/**
	 * @param key
	 * @return
	 * @throws IOException 
	 * @throws InvalidPathException 
	 */
	String convertStringEnc(String origString, Key key) throws InvalidPathException, IOException;
	/**
	 * @param key
	 * @return
	 * @throws IOException 
	 * @throws InvalidPathException 
	 */
	String convertStringDec(String origString, Key key) throws InvalidPathException, IOException;
	
	
	String getAlgorithmName();
}
