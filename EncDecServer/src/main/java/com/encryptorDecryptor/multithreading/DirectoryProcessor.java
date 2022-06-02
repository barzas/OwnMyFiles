package com.encryptorDecryptor.multithreading;

import com.encryptorDecryptor.encryption.logs.EncryptionLog4JLogger;

import java.io.File;
import java.io.IOException;

public abstract class DirectoryProcessor implements IDirectoryProcessor{
	
	protected void checkFileErrors(File subDir, File dir, Class<?> origClass) throws IOException {
		if(!subDir.mkdir()) EncryptionLog4JLogger.warn("creation of new sub directory has failed, it might already exist", this.getClass());
		System.out.println(dir.getAbsolutePath());
		if(dir.createNewFile()) EncryptionLog4JLogger.error("path given to a directory that doesn't exist", origClass);
		else if(!dir.isDirectory()) EncryptionLog4JLogger.error("path given to a file that isn't a directory", origClass);
	}
	
	protected void encryptionFilesValidation(File encryptedFolder) throws IOException { //todo- throw to front
		if(encryptedFolder.createNewFile()) EncryptionLog4JLogger.error("encrypted file doesn't exist", this.getClass());
		else if(!encryptedFolder.isDirectory()) EncryptionLog4JLogger.error("encrypted file isn't a folder", this.getClass());
		//if(!new File(keyPath).exists()) EncryptionLog4JLogger.fatal("key file to decrypt with not found", this.getClass());
	}
}
