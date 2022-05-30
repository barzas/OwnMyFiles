package input.handling;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Testing {

	public static void main(String[] args) throws JAXBException {
		File file = new File("C:\\Users\\shach\\eclipse-workspace\\EncryptionProject\\testFile.xml");
		JAXBContext context = JAXBContext.newInstance(ProcessSettings.class);
		Unmarshaller unmarshall = context.createUnmarshaller();
		ProcessSettings reader = (ProcessSettings)unmarshall.unmarshal(file);
		ProcessSettings myObj = new ProcessSettings(reader.getEncAlgoStr(), reader.getKeyPath()
				,reader.getOrigPath(), reader.getEncryptPath(), reader.getDecryptPath());
		System.out.println(myObj.getEncAlgo().getAlgorithmName());
	}
}
