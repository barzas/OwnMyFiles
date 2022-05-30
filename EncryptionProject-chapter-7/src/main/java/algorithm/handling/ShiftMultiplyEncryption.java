package algorithm.handling;

import java.math.*;

import key.Key;

public class ShiftMultiplyEncryption extends AtomicEncryptions {
	private int keyInverse;
	private int keyHolder = -1;
	private int FORBIDDEN_CHARS_BUFFER = Key.getMaxValue();
	
	@Override
	public String convertStringEnc(String origString, Key key) {
		StringBuilder convertedString = new StringBuilder(); 
		int newChar;
		for(int i = 0; i < origString.length(); i++){ 
			newChar = encryptChar(origString.charAt(i), key);
			if(origString.charAt(i) > FORBIDDEN_CHARS_BUFFER || newChar < AVOID_SPECIAL_CHARACTERS)
				convertedString.append((char)(origString.charAt(i) + FORBIDDEN_CHARS_BUFFER));
			else
				convertedString.append((char)newChar);
		} 
		return convertedString.toString();
	}
	
	/**
	 *
	 */
	@Override
	public String convertStringDec(String origString, Key key) {
		StringBuilder convertedString = new StringBuilder(); 
		char newChar;
		for(int i = 0; i < origString.length(); i++){ 
			if(origString.charAt(i) > FORBIDDEN_CHARS_BUFFER) {
				convertedString.append((char)(origString.charAt(i) - FORBIDDEN_CHARS_BUFFER));
			}
			else {
				newChar = decryptChar(origString.charAt(i), Key.getMaxValue(), key.getKey());
				convertedString.append(newChar);
			}
		} 
		return convertedString.toString();
	} 
	
	private synchronized int encryptChar(char origChar, Key key) {
		return (origChar * key.getKey()) % Key.getMaxValue();
	}
	
	/**
	 * @param oldChar
	 * @param moduloBase
	 * @param key
	 * @return
	 */
	private synchronized char decryptChar(int oldChar, int moduloBase, int key) {
		if(key != this.keyHolder) {
			this.keyHolder = key;
			BigInteger keyBI = new BigInteger(String.valueOf(key));
			BigInteger modBase = new BigInteger(String.valueOf(moduloBase));
			this.keyInverse = keyBI.modInverse(modBase).intValue();
		}
		return (char)(oldChar * this.keyInverse % moduloBase);
	}
	
	@Override
	public String getAlgorithmName() {
		return "Shift Multiply Encryption";
	}
}
