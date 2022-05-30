package file.handling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;

import key.Key;

public class KeyFileActions {
	/**
	 * @param keyPath
	 * @throws IOException
	 * @throws InvalidPathException
	 */
	public static void writeKeyFile(Key key) throws IOException, InvalidPathException { //write a random key to the key file
		Paths.get(key.getPath());
		File keyFile = new File(key.getPath());
		FileWriter keyWriter = new FileWriter(keyFile, true);
		keyWriter.write(String.valueOf(key.getKey()) + System.lineSeparator());
        keyWriter.close();
	}
	
	/**
	 * @param keyPath
	 * @return
	 * @throws IOException
	 * @throws InvalidPathException
	 */
	public static ArrayList<Integer> readKeyFile(String keyPath) throws IOException, InvalidPathException { //read only the first key from the key file 
        Paths.get(keyPath);
		ArrayList<Integer> keyArr = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(keyPath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	keyArr.add(Integer.parseInt(line));
		    }
			return keyArr;
		}
	}

}
