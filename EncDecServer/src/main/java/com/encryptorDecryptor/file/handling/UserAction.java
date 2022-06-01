package com.encryptorDecryptor.file.handling;

import com.encryptorDecryptor.algorithm.handling.*;

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

    public IEncryptionAlgorithm getAlgo(String encryptType) {
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
                System.out.println("can't find your chosen algorithm. please try again");
                break;
        }
        return encryptAlgo;
    }

    public IEncryptionAlgorithm getAlgoByInput() {
        System.out.println("please enter shiftUp / shiftMultiply / double / xor / repeat");
        String algo = reader.nextLine();
        while (!algo.equals("shiftUp") && !algo.equals("shiftMultiply") && !algo.equals("double") && !algo.equals("xor") && !algo.equals("repeat")) {
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
