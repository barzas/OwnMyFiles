package shahar_flumin.EncryptionProject;
import java.io.*;
import java.nio.file.InvalidPathException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import algorithm.handling.DoubleEncryption;
import algorithm.handling.RepeatEncryption;
import algorithm.handling.ShiftMultiplyEncryption;
import algorithm.handling.ShiftUpEncryption;
import algorithm.handling.XorEncryption;
import exceptions.InvalidEncryptionAlgorithmTypeException;
import file.handling.FileEncryptor;
import file.handling.KeyFileActions;
import key.Key;

class FileEncryptorTest {
	static String ORIG_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester.txt";
	static String KEY_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\key.txt";
	static String OUTPUT_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester_encrypted.txt";
	static String DECRYPT_FILE_PATH = "C:\\Users\\shach\\eclipse-workspace\\encryptor\\src\\encryptor\\tester_decrypted.txt";
	
	public void testEqualFiles(File f1, File f2){
		try (BufferedReader br1 = new BufferedReader(new FileReader(f1)); 
				BufferedReader br2 = new BufferedReader(new FileReader(f2))) {
			
			   String line1;
			   String line2 = " ";
			   while ((line1 = br1.readLine()) != null) {
			       line2 = br2.readLine();
			       assertEquals(line1, line2);
			       
			   }
			   line2 = br2.readLine();
			   assertEquals(line1, line2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testShiftUp(){
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		ShiftUpEncryption sue = new ShiftUpEncryption();
		FileEncryptor fe = new FileEncryptor(sue);
		try {
			try {
				fe.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				fe.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			File orig = new File(ORIG_FILE_PATH);
			File decrypted = new File(DECRYPT_FILE_PATH);
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testShiftMultiply(){
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		ShiftMultiplyEncryption sme = new ShiftMultiplyEncryption();
		FileEncryptor fe = new FileEncryptor(sme);
		try {
			try {
				fe.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				fe.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			File orig = new File(ORIG_FILE_PATH);
			File decrypted = new File(DECRYPT_FILE_PATH);
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testXor() {
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		XorEncryption xe = new XorEncryption();
		FileEncryptor fe = new FileEncryptor(xe);
		try {
			try {
				fe.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				fe.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			File orig = new File(ORIG_FILE_PATH);
			File decrypted = new File(DECRYPT_FILE_PATH);
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testRepeatShiftUp() {
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		RepeatEncryption re;
		int n = 10;
		ShiftUpEncryption sue = new ShiftUpEncryption();
		File orig = new File(ORIG_FILE_PATH);
		File decrypted = new File(DECRYPT_FILE_PATH);
		try {
			re = new RepeatEncryption(sue, n);
			FileEncryptor feShift = new FileEncryptor(re);
			try {
				feShift.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				feShift.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRepeatShiftMult() {
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		RepeatEncryption re;
		int n = 10;
		ShiftMultiplyEncryption sme = new ShiftMultiplyEncryption();
		File orig = new File(ORIG_FILE_PATH);
		File decrypted = new File(DECRYPT_FILE_PATH);
		try {
			re = new RepeatEncryption(sme, n);
			FileEncryptor feShift = new FileEncryptor(re);
			try {
				feShift.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				feShift.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRepeatXor() {
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		RepeatEncryption re;
		int n = 10;
		XorEncryption xe = new XorEncryption();
		File orig = new File(ORIG_FILE_PATH);
		File decrypted = new File(DECRYPT_FILE_PATH);
		try {
			re = new RepeatEncryption(xe, n);
			FileEncryptor feShift = new FileEncryptor(re);
			try {
				feShift.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				feShift.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDoubleShiftUp() {
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		DoubleEncryption de;
		ShiftUpEncryption sue = new ShiftUpEncryption();
		File orig = new File(ORIG_FILE_PATH);
		File decrypted = new File(DECRYPT_FILE_PATH);
		try {
			de = new DoubleEncryption(sue);
			FileEncryptor feShift = new FileEncryptor(de);
			try {
				feShift.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				feShift.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDoubleShiftMult() {
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		DoubleEncryption de;
		ShiftMultiplyEncryption sme = new ShiftMultiplyEncryption();
		File orig = new File(ORIG_FILE_PATH);
		File decrypted = new File(DECRYPT_FILE_PATH);
		try {
			de = new DoubleEncryption(sme);
			FileEncryptor feShift = new FileEncryptor(de);
			try {
				feShift.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				feShift.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDoubleXor() {
		File keyFile = new File(KEY_PATH);
		keyFile.delete();
		DoubleEncryption de;
		XorEncryption xe = new XorEncryption();
		File orig = new File(ORIG_FILE_PATH);
		File decrypted = new File(DECRYPT_FILE_PATH);
		try {
			de = new DoubleEncryption(xe);
			FileEncryptor feShift = new FileEncryptor(de);
			try {
				feShift.handleEncryption(ORIG_FILE_PATH, OUTPUT_FILE_PATH, KEY_PATH);
				feShift.handleDecryption(OUTPUT_FILE_PATH, DECRYPT_FILE_PATH, KEY_PATH);
			} catch (InvalidPathException | InvalidEncryptionAlgorithmTypeException e) {
				e.printStackTrace();
			}
			testEqualFiles(orig, decrypted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testKeyReadWrite() {
		Key key = new Key(KEY_PATH);
		File keyFile = new File(KEY_PATH);
		try {
			if(!keyFile.createNewFile()) keyFile.delete();
			KeyFileActions.writeKeyFile(key);
			assertEquals(key.getKey(), KeyFileActions.readKeyFile(KEY_PATH).get(0));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testStringConvertion() {
		String testText = "abcdefg\nhijklmnop\r\n14891kljdas";
		ShiftUpEncryption sue = new ShiftUpEncryption();
		ShiftMultiplyEncryption sme = new ShiftMultiplyEncryption();
		XorEncryption xe = new XorEncryption();
		Key key = new Key(KEY_PATH);

		String encryptedText = sue.convertStringEnc(testText, key);
		String result = sue.convertStringDec(encryptedText, key);
		assertEquals(testText, result);
		
		encryptedText = sme.convertStringEnc(testText, key);
		result = sme.convertStringDec(encryptedText, key);
		assertEquals(testText, result);
		
		encryptedText = xe.convertStringEnc(testText, key);
		result = xe.convertStringDec(encryptedText, key);
		assertEquals(testText, result);
	}
	
	
}
