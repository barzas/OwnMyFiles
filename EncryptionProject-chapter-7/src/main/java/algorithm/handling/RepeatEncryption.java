package algorithm.handling;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import key.Key;

public class RepeatEncryption extends ComplexEncryptions {
	private EncryptionAlgorithmImp enc;
	private int n;
	
	public RepeatEncryption(EncryptionAlgorithmImp enc, int n) {
		this.enc = enc;
		this.n = n;
	}
	
	public EncryptionAlgorithmImp getSubEncryption() {
		return this.enc;
	}
	
	public int getN() {
		return this.n;
	}

	@Override
	public String convertStringEnc(String origString, Key key) throws InvalidPathException, IOException {
		for(int i = 0; i < n; i++) {
			origString = enc.convertStringEnc(origString, key);
		}
		return origString;
	}

	@Override
	public String convertStringDec(String origString, Key key) throws InvalidPathException, IOException {
		for(int i = 0; i < n; i++) {
			origString = enc.convertStringDec(origString, key);
		}
		return origString;
	}
	
	@Override
	public String getAlgorithmName() {
		return "Repeat Encryption";
	}
}
