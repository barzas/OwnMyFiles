package encryption.logs;

import algorithm.handling.IEncryptionAlgorithm;

public interface IEncryptionTimeEventsListener {
	void encryptionStarted(String origFilePath, Class<?> origClass);
	void encryptionEnded(String origFilePath, IEncryptionAlgorithm ea, String encFilePath, double time, Class<?> origClass);
	void decryptionStarted(String encFilePath, Class<?> origClass);
	void decryptionEnded(String encFilePath, IEncryptionAlgorithm ea, String decFilePath, double time, Class<?> origClass);
	void directoryEncryptionStarted(String directoryPath, Class<?> origClass);
	void directoryEncryptionEnded(String directoryPath, IEncryptionAlgorithm ea, double time, Class<?> origClass);
	void directoryDecryptionStarted(String directoryPath, Class<?> origClass);
	void directoryDecryptionEnded(String directoryPath, IEncryptionAlgorithm ea, double time, Class<?> origClass);


}
