package com.encryptorDecryptor.multithreading;

import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public interface IDirectoryProcessor {
	void encryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException, InterruptedException;
	void decryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException, InterruptedException;
}
