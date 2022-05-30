package encryption.logs;

import java.util.Observable;
import java.util.Observer;

public class EncryptionEventObserver implements Observer{
	private static EncryptionLogger log = new EncryptionLogger();

	@Override
	public void update(Observable o, Object obInfo) {
		ObserverInfo info = (ObserverInfo)obInfo;
		switch(info.getEvent()) {
		case ENCRYPTION_STARTED:
			log.encryptionStarted(info.getOrigFilePath(), info.getOrigClass());
			break;
		
		case ENCRYPTION_ENDED:
			log.encryptionEnded(info.getOrigFilePath(), info.getEncryptionAlgorithm(), info.getTargetFilePath(), info.getTime(), info.getOrigClass());
			break;
			
		case DECRYPTION_STARTED:
			log.decryptionStarted(info.getOrigFilePath(), info.getOrigClass());
			break;
			
		case DECRYPTION_ENDED:
			log.decryptionEnded(info.getOrigFilePath(), info.getEncryptionAlgorithm(), info.getTargetFilePath(), info.getTime(), info.getOrigClass());
			break;
		
		case DIRECTORY_ENCRYPTION_STARTED:
			log.directoryEncryptionStarted(info.getOrigFilePath(), info.getOrigClass());
			break;
			
		case DIRECTORY_ENCRYPTION_ENDED:
			log.directoryEncryptionEnded(info.getOrigFilePath(), info.getEncryptionAlgorithm(), info.getTime(), info.getOrigClass());
			break;
			
		case DIRECTORY_DECRYPTION_STARTED:
			log.directoryDecryptionStarted(info.getOrigFilePath(), info.getOrigClass());
			break;
			
		case DIRECTORY_DECRYPTION_ENDED:
			log.directoryDecryptionEnded(info.getOrigFilePath(), info.getEncryptionAlgorithm(), info.getTime(), info.getOrigClass());
			break;
		}
		
	}

	
}
