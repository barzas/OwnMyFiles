package encryption.logs;

import algorithm.handling.AtomicEncryptions;
import algorithm.handling.ComplexEncryptions;
import algorithm.handling.IEncryptionAlgorithm;


public class EncryptionLogger implements IEncryptionTimeEventsListener {

	@Override
	public synchronized void encryptionStarted(String origFilePath, Class<?> origClass) {
		String message = "Encryption started for file: " + origFilePath;
		EncryptionLog4JLogger.info(message, origClass);
	}

	@Override
	public synchronized void encryptionEnded(String origFilePath, IEncryptionAlgorithm ea, String encFilePath, double time, Class<?> origClass) {
		ComplexEncryptions atomicEncryption;
		String atomicEncryptionName = "";
		if(!(ea instanceof AtomicEncryptions)) {
			atomicEncryption = (ComplexEncryptions)ea;
			atomicEncryptionName = atomicEncryption.getSubEncryption().getAlgorithmName();
		}
		String message = String.format("The encryption for file: %s with algorithm %s took %f miliseconds. "
				+ "The encrypted file is located in file %s", origFilePath, ea.getAlgorithmName() + " " + atomicEncryptionName, time, encFilePath);
		EncryptionLog4JLogger.info(message, origClass);
	}

	@Override
	public synchronized void decryptionStarted(String encFilePath, Class<?> origClass) {
		String message = "Decryption started for file: " + encFilePath;
		EncryptionLog4JLogger.info(message, origClass);
	}

	@Override
	public synchronized void decryptionEnded(String encFilePath, IEncryptionAlgorithm ea, String decFilePath, double time, Class<?> origClass) {
		ComplexEncryptions atomicEncryption;
		String atomicEncryptionName = "";
		if(!(ea instanceof AtomicEncryptions)) {
			atomicEncryption = (ComplexEncryptions)ea;
			atomicEncryptionName = atomicEncryption.getSubEncryption().getAlgorithmName();
		}
		String message = String.format("The decryption for file %s with algorithm %s took %f miliseconds. "
				+ "The decrypted file is located in file %s", decFilePath, ea.getAlgorithmName() + " " + atomicEncryptionName, time, decFilePath);
		EncryptionLog4JLogger.info(message, origClass);
	}
	
	@Override
	public synchronized void directoryEncryptionStarted(String directoryPath, Class<?> origClass) {
		String message = String.format("Encryption for directory %s has started", directoryPath);
		EncryptionLog4JLogger.info(message, origClass);
	}
	
	@Override
	public synchronized void directoryEncryptionEnded(String directoryPath, IEncryptionAlgorithm ea, double time, Class<?> origClass) {
		String message = String.format("The Encryption for directory %s with algorithm %s took %f miliseconds.", directoryPath, ea.getAlgorithmName(), time);
		EncryptionLog4JLogger.info(message, origClass);
	}

	@Override
	public synchronized void directoryDecryptionStarted(String directoryPath, Class<?> origClass) {
		String message = String.format("Decryption for directory %s has started", directoryPath);
		EncryptionLog4JLogger.info(message, origClass);
	}
	
	@Override
	public synchronized void directoryDecryptionEnded(String directoryPath, IEncryptionAlgorithm ea, double time, Class<?> origClass) {
		ComplexEncryptions atomicEncryption;
		String atomicEncryptionName = "";
		if(!(ea instanceof AtomicEncryptions)) {
			atomicEncryption = (ComplexEncryptions)ea;
			atomicEncryptionName = atomicEncryption.getSubEncryption().getAlgorithmName();
		}
		String message = String.format("The Decryption for directory %s with algorithm %s took %f miliseconds.", directoryPath, ea.getAlgorithmName() + " " + atomicEncryptionName, time);
		EncryptionLog4JLogger.info(message, origClass);
	}
	
}
