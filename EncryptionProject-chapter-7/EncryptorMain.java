package encryptor;

import java.util.Scanner;

public class EncryptorMain extends Encryptor{

	public static Scanner reader = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Please type 0 to encrypt and 1 to decrypt");
		String userChoice = DEFAULT;
		handleInput(userChoice);

	}

}
