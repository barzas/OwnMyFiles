package multithreading;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import exceptions.InvalidEncryptionAlgorithmTypeException;

public interface IDirectoryProcessor {
	void encryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException, InterruptedException;
	void decryptDirectory(String dirPath) throws IOException, InvalidPathException, SecurityException, InvalidEncryptionAlgorithmTypeException, InterruptedException;
}
