package input.handling;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Observer;

import encryption.logs.EncryptionEventObserver;
import encryption.logs.EncryptionLog4JLogger;
import exceptions.InvalidEncryptionAlgorithmTypeException;
import file.handling.FileEncryptor;
import multithreading.AsyncDirectoryProcessor;

public class XMLReading {
	private FileEncryptor fileEnc;
	private File xmlFile;
	
	public XMLReading(File file) throws JAXBException {
		String filePath = file.getAbsolutePath();
		if(!filePath.substring(filePath.lastIndexOf('.'), filePath.length()).equals(".xml"))
			EncryptionLog4JLogger.error("wrong file type given", this.getClass());
		ProcessSettings reader = readFile(file);
		this.fileEnc = new FileEncryptor(reader.getEncAlgo());
		this.xmlFile = file;
	}
	
	public void handleEncrypt() throws JAXBException, InvalidPathException, IOException, InvalidEncryptionAlgorithmTypeException, SecurityException, InterruptedException   {
		String filePath = this.xmlFile.getAbsolutePath();
		ProcessSettings encryptionInfo = readFile(new File(filePath));
		if(encryptionInfo.getOrigPath().equals("")) 
			EncryptionLog4JLogger.error("no source file path given", this.getClass());
		if(!encryptionInfo.getEncryptPath().equals("") && !encryptionInfo.getDecryptPath().equals("") && !encryptionInfo.getKeyPath().equals("")) {
			fileEnc.handleEncryption(encryptionInfo.getOrigPath(), encryptionInfo.getEncryptPath(), encryptionInfo.getKeyPath());
		}
		else {
			AsyncDirectoryProcessor encryptDir = new AsyncDirectoryProcessor(this.fileEnc);
			encryptDir.encryptDirectory(encryptionInfo.getOrigPath());
		}
	}
	
	public void handleDecrypt() throws InvalidPathException, IOException, InvalidEncryptionAlgorithmTypeException, JAXBException, SecurityException, InterruptedException {
		String filePath = this.xmlFile.getAbsolutePath();
		ProcessSettings encryptionInfo = readFile(new File(filePath));
		if(encryptionInfo.getOrigPath().equals("")) 
			EncryptionLog4JLogger.error("no source file path given", this.getClass());
		if(!encryptionInfo.getEncryptPath().equals("") && !encryptionInfo.getDecryptPath().equals("") && !encryptionInfo.getKeyPath().equals("")) {
			fileEnc.handleDecryption(encryptionInfo.getEncryptPath(), encryptionInfo.getDecryptPath(), encryptionInfo.getKeyPath());
		}
		else {
			AsyncDirectoryProcessor decryptDir = new AsyncDirectoryProcessor(this.fileEnc);
			decryptDir.decryptDirectory(encryptionInfo.getOrigPath());
		}
	}
	
	public ProcessSettings readFile(File file) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ProcessSettings.class);
		Unmarshaller unmarshall = context.createUnmarshaller();
		ProcessSettings reader = (ProcessSettings)unmarshall.unmarshal(file);
		ProcessSettings encryptionInfo = new ProcessSettings(reader.getEncAlgoStr(), reader.getKeyPath()
				,reader.getOrigPath(), reader.getEncryptPath(), reader.getDecryptPath());
		return encryptionInfo;
	}
	
	public void addObserver(EncryptionEventObserver observer) {
		this.fileEnc.addObserver(observer);
	}
	
}
