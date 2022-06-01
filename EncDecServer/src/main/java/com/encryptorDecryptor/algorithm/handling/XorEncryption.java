package com.encryptorDecryptor.algorithm.handling;

import com.encryptorDecryptor.key.Key;

public class XorEncryption extends AtomicEncryptions {

	@Override
	public String convertStringEnc(String origString, Key key) {
		StringBuilder convertedString = new StringBuilder(); 
		int c;
		for(int i = 0; i < origString.length(); i++){ 
			c = (origString.charAt(i) ^ key.getKey()) + AVOID_SPECIAL_CHARACTERS;
			convertedString.append((char)c);
		}
		return convertedString.toString();
	}
	
	@Override
	public String convertStringDec(String origString, Key key) {
		StringBuilder convertedString = new StringBuilder(); 
		for(int i = 0; i < origString.length(); i++){ 
			convertedString.append(decryptChar(origString.charAt(i), key.getKey())); 
		}
		return convertedString.toString();
	} 
	
	/**
	 * @param oldCharValue
	 * @param key
	 * @return
	 */
	private char decryptChar(int oldCharValue, int key) {
		int newChar = oldCharValue - AVOID_SPECIAL_CHARACTERS;
		return (char)((int)newChar ^ key);
	}
	
	@Override
	public String getAlgorithmName() {
		return "Xor Encryption";
	}
}
