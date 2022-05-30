package algorithm.handling;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import key.Key;

public interface IEncryptionAlgorithm {
	/**
	 * @param str
	 * @param key
	 * @return
	 * @throws IOException 
	 * @throws InvalidPathException 
	 */
	String convertStringEnc(String origString, Key key) throws InvalidPathException, IOException;
	/**
	 * @param str
	 * @param key
	 * @return
	 * @throws IOException 
	 * @throws InvalidPathException 
	 */
	String convertStringDec(String origString, Key key) throws InvalidPathException, IOException;
	
	
	String getAlgorithmName();
}
