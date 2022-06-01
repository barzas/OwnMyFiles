package com.encryptorDecryptor.encryption.logs;

import com.encryptorDecryptor.algorithm.handling.IEncryptionAlgorithm;
import com.encryptorDecryptor.enums.EncryptionEventEnum;

public class ObserverInfo {
	private EncryptionEventEnum event;
	private String origFilePath;
	private IEncryptionAlgorithm encAlgo;
	private String targetFilePath;
	private double time;
	private Class<?> origClass;

	public ObserverInfo(EncryptionEventEnum event, String origFilePath, IEncryptionAlgorithm encAlgo, String targetFilePath,
			double time, Class<?> origClass) {
		this.event = event;
		this.origFilePath = origFilePath;
		this.encAlgo = encAlgo;
		this.targetFilePath = targetFilePath;
		this.time = time;
		this.origClass = origClass;
	}
	
	public ObserverInfo(EncryptionEventEnum event, String origFilePath, IEncryptionAlgorithm encAlgo, double time, Class<?> origClass) {
		this.event = event;
		this.origFilePath = origFilePath;
		this.encAlgo = encAlgo;
		this.targetFilePath = null;
		this.time = time;
		this.origClass = origClass;
	}
	
	public EncryptionEventEnum getEvent() {
		return event;
	}


	public void setEvent(EncryptionEventEnum event) {
		this.event = event;
	}


	public IEncryptionAlgorithm getEncryptionAlgorithm() {
		return encAlgo;
	}


	public void setEncryptionAlgorithm(IEncryptionAlgorithm encAlgo) {
		this.encAlgo = encAlgo;
	}

	
	public String getOrigFilePath() {
		return origFilePath;
	}

	public void setOrigFilePath(String origFilePath) {
		this.origFilePath = origFilePath;
	}

	public String getTargetFilePath() {
		return targetFilePath;
	}

	public void setTargetFilePath(String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}

	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}
	
	public Class<?> getOrigClass() {
		return origClass;
	}

	public void setOrigClass(Class<?> origClass) {
		this.origClass = origClass;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encAlgo == null) ? 0 : encAlgo.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((origFilePath == null) ? 0 : origFilePath.hashCode());
		result = prime * result + ((targetFilePath == null) ? 0 : targetFilePath.hashCode());
		long temp;
		temp = Double.doubleToLongBits(time);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		ObserverInfo other = (ObserverInfo) obj;
		if(!encAlgo.equals(other.encAlgo)) return false;
		if(event != other.event) return false;
		if(!origFilePath.equals(other.origFilePath)) return false;
		if(!targetFilePath.equals(other.targetFilePath)) return false;
		if(Double.doubleToLongBits(time) != Double.doubleToLongBits(other.time)) return false;
		return true;
	}
}
