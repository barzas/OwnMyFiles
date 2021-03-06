package com.encryptorDecryptor.algorithm.handling;


import com.encryptorDecryptor.file.handling.KeyFileActions;
import com.encryptorDecryptor.key.Key;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public class DoubleEncryption extends ComplexEncryptions{
	private EncryptionAlgorithmImp enc;
	private boolean secondKeyFlag = false;
	private int secondKeyVal;
	
	public DoubleEncryption(EncryptionAlgorithmImp enc) {
		this.enc = enc;
	}	
	
	public EncryptionAlgorithmImp getSubEncryption() {
		return this.enc;
	}
	
	@Override
	public String convertStringEnc(String origString, Key key) throws InvalidPathException, IOException {
		Key secondKey;
		if(!secondKeyFlag) {
			secondKey = new Key(key.getPath());
	//		KeyFileActions.writeKeyFile(secondKey);
			secondKeyFlag = true;
			secondKeyVal = secondKey.getKey();
		}
		else {
			secondKey = new Key(key.getPath(), this.secondKeyVal);
		}
		String firstConversion = enc.convertStringEnc(origString, key);
		String secondConversion = enc.convertStringEnc(firstConversion, secondKey);
		return secondConversion;
	}

	@Override
	public String convertStringDec(String origString, Key key) throws InvalidPathException, IOException {
		Key secondKey = new Key(secondKeyVal);
		String firstConversion = enc.convertStringDec(origString, secondKey);
		String secondConversion = enc.convertStringDec(firstConversion, key);
		return secondConversion;
	}
	
	@Override
	public String getAlgorithmName() {
		return "Double Encryption";
	}
}
