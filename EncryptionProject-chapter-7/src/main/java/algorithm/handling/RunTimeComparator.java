package algorithm.handling;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Comparator;

import encryption.logs.EncryptionLog4JLogger;
import key.Key;

public class RunTimeComparator implements Comparator<EncryptionAlgorithmImp> {
	
	public int compare(EncryptionAlgorithmImp alg1, EncryptionAlgorithmImp alg2) {
		String testText = "H".repeat(100000);
		Key key = new Key();
		long startTime1 = System.nanoTime();
		try {
			alg1.convertStringEnc(testText, key);
		} catch (InvalidPathException | IOException e) {
			EncryptionLog4JLogger.error("File operations error in converting test string", this.getClass());
		}
		long endTime1 = System.nanoTime();
		long duration1 = (endTime1 - startTime1);
		
		long startTime2 = System.nanoTime();
		try {
			alg2.convertStringEnc(testText, key);
		} catch (InvalidPathException | IOException e) {
			EncryptionLog4JLogger.error("File operations error in converting test string", this.getClass());
		}
		long endTime2 = System.nanoTime();
		long duration2 = (endTime2 - startTime2);
		return Long.compare(duration1, duration2);
	}
}
