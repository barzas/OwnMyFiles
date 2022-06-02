package com.encryptorDecryptor.key;

import com.encryptorDecryptor.encryption.logs.EncryptionLog4JLogger;

import java.util.ArrayList;
import java.util.Random;

public class Key {
	private static int maxValue = (int)Math.sqrt(65536);
	private int key;
	private int secondKey;
	private String path;
	
	public Key(String path, int key) {
		this.key = key;
		this.path = path;
	}
	
	public Key(String path) {
		this.path = path;
		this.key = generateKey();
	}
	
	public Key(int key) {
		this.key = key;
	}

	public Key(int key, int secondKey) {
		this.key = key;
		this.secondKey = secondKey;
	}
	
	public Key() {
		this.path = null;
		this.key = generateKey();
	}
	
	public int getKey() {
		return key;
	}
	
	public String getPath() {
		return path;
	}

	public int getSecondKey() {
		return secondKey;
	}

	/**
	 * @return
	 */
	public static int generateKey() {
	//	int key = (int)((Math.random() * (133))+ 122);

		ArrayList<Integer> arr = getPrimes();
		Random rand = new Random();
		int randNum = rand.nextInt(arr.size());
		int key = arr.get(randNum);
		EncryptionLog4JLogger.debug(String.format("value of key is: %d", key), Key.class);
		return key;
	}
	
	/**
	 * @return
	 */
	private static ArrayList<Integer> getPrimes(){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		if(iscoprime(maxValue, 2)) arr.add(2);
		for(int i = 3; i < maxValue; i += 2) {
			if(isPrime(i)) if(iscoprime(maxValue, i)) arr.add(i);
		}
		return arr;
	}
	
	/**
	 * @param num
	 * @return
	 */
	private static boolean isPrime(int num) {
		for(int i = 3; i < Math.sqrt(maxValue); i += 2) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	/**
	 * @param num1
	 * @param num2
	 * @return
	 */
	private static int gcd(int num1, int num2)
    {
        if (num1 == 0 || num2 == 0)
            return 0;
         
        if (num1 == num2)
            return num1;
         
        if (num1 > num2)
            return gcd(num1 - num2, num2);
                 
        return gcd(num1, num2 - num1);
    }
 
    /**
     * @param num1
     * @param num2
     * @return
     */
    private static boolean iscoprime(int num1, int num2) {
        return (gcd(num1, num2) == 1);
    }
    
	/**
	 * @return
	 */
	public static int getMaxValue() {
		return maxValue;
	}
	
	public static int getKeyStrength() {
		return String.valueOf(Key.getMaxValue() - 1).length();
	}
}
