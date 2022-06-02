package com.encryptorDecryptor.file.handling;

import com.encryptorDecryptor.algorithm.handling.*;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;

import java.io.File;
import java.util.Scanner;

public class UserAction {

    public static Scanner reader = new Scanner(System.in);

    public String getAction() {
        System.out.println("please enter encrypt / decrypt");
        String action = reader.nextLine();
        while (!action.equals("encrypt") && !action.equals("decrypt")) {
            System.out.println("can't find your chosen action. please try again");
            System.out.println("please enter encrypt / decrypt");
            action = reader.nextLine();
        }
        return action;
    }

    public IEncryptionAlgorithm getAlgo(String encryptType) throws InvalidEncryptionAlgorithmTypeException {
        IEncryptionAlgorithm encryptAlgo = new ShiftUpEncryption();
        switch (encryptType) {
            case "ShiftUp":
                break;
            case "ShiftMultiply":
                encryptAlgo = new ShiftMultiplyEncryption();
                break;
            case "Double":
                encryptAlgo = new DoubleEncryption(new ShiftUpEncryption());
                break;
            case "Xor":
                encryptAlgo = new XorEncryption();
                break;
            case "Repeat":
                encryptAlgo = new RepeatEncryption(new ShiftUpEncryption(), 5);
                break;
            default:
                throw new InvalidEncryptionAlgorithmTypeException("can't find your chosen algorithm type. please try again");
        }
        return encryptAlgo;
    }

    public IEncryptionAlgorithm getAlgoByInput() throws InvalidEncryptionAlgorithmTypeException {
        System.out.println("please enter ShiftUp / ShiftMultiply / Double / Xor / Repeat");
        String algo = reader.nextLine();
        while (!algo.equals("ShiftUp") && !algo.equals("ShiftMultiply") && !algo.equals("Double") && !algo.equals("Xor") && !algo.equals("Repeat")) {
            System.out.println("can't find your chosen action. please try again");
            System.out.println("please enter shiftUp / shiftMultiply / double / xor / repeat");
            algo = reader.nextLine();
        }
        return getAlgo(algo);
    }


    public String getPath() {
        System.out.println("please enter path to your directory to encrypt");
        String dirPath = reader.nextLine();
        while (!(new File(dirPath).exists())) {
            System.out.println("can't find file. please try again");
            System.out.println("please enter path to your directory to encrypt");
            dirPath = reader.nextLine();
        }
        return dirPath;
    }

}
