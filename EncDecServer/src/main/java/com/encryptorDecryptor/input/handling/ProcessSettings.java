package com.encryptorDecryptor.input.handling;

import com.encryptorDecryptor.algorithm.handling.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProcessSettings")
public class ProcessSettings {
	private String encAlgoStr;
	private String keyPath;
	private String origPath;
	private String encryptPath;
	private String decryptPath;
	private EncryptionAlgorithmImp encAlgo;
	
	public ProcessSettings(String encAlgoStr, String keyPath, String origPath, String encryptPath, String decryptPath) {
		strToEncAlgo(encAlgoStr);
		this.encAlgoStr = encAlgoStr;
		this.keyPath = keyPath;
		this.origPath = origPath;
		this.encryptPath = encryptPath;
		this.decryptPath = decryptPath;
	}
	
	public ProcessSettings() {
	}
	
	public EncryptionAlgorithmImp getEncAlgo() {
		return this.encAlgo;
	}
	public void setEncAlgo(EncryptionAlgorithmImp encAlgo) {
		this.encAlgo = encAlgo;
	}
	
	public EncryptionAlgorithmImp strToEncAlgo(String encAlgoStr) {
		switch(encAlgoStr) {
		case "ShiftUpEncryption":
			this.encAlgo = new ShiftUpEncryption();
			break;
			
		case "ShiftMultiplyEncryption":
			this.encAlgo = new ShiftMultiplyEncryption();
			break;
			
		case "XorEncryption":
			this.encAlgo = new XorEncryption();
			break;
			
		case "DoubleShiftUp":
			this.encAlgo = new DoubleEncryption(new ShiftUpEncryption());
			break;
			
		case "DoubleShiftMultiply":
			this.encAlgo = new DoubleEncryption(new ShiftMultiplyEncryption());
			break;
			
		case "DoubleXor":
			this.encAlgo = new DoubleEncryption(new XorEncryption());
			break;
			
		case "RepeatShiftUp":
			this.encAlgo = new RepeatEncryption(new ShiftUpEncryption(), 5);
			break;
			
		case "RepeatShiftMultiply":
			this.encAlgo = new RepeatEncryption(new ShiftMultiplyEncryption(), 5);
			break;
			
		case "RepeatXor":
			this.encAlgo = new RepeatEncryption(new XorEncryption(), 5);
			break;
		}
		return this.encAlgo;
	}
	
	@XmlElement(name="Algorithm")
	public String getEncAlgoStr() {
		return encAlgoStr;
	}
	public void setEncAlgoStr(String encAlgoStr) {
		this.encAlgoStr = encAlgoStr;
	}
	
    @XmlElement(name="KeyPath")
	public String getKeyPath() {
		return keyPath;
	}
	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}
	
    @XmlElement(name="OriginalPath")
	public String getOrigPath() {
		return origPath;
	}
	public void setOrigPath(String origPath) {
		this.origPath = origPath;
	}
	
    @XmlElement(name="EncryptionPath")
	public String getEncryptPath() {
		return encryptPath;
	}
	public void setEncryptPath(String encryptPath) {
		this.encryptPath = encryptPath;
	}
	
	@XmlElement(name="DecryptionPath")
	public String getDecryptPath() {
		return decryptPath;
	}
	public void setDecryptPath(String decryptPath) {
		this.decryptPath = decryptPath;
	}
}
