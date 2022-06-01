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

    public IEncryptionAlgorithm getAlgo() {
        IEncryptionAlgorithm encryptAlgo = new ShiftUpEncryption();
        boolean isAlgoDefined = false;
        while (!isAlgoDefined) {
            System.out.println("please enter shiftUp / shiftMultiply / double / xor / repeat");
            String encryptType = reader.nextLine();
            switch (encryptType) {
                case "shiftUp":
                    isAlgoDefined = true;
                    break;
                case "shiftMultiply":
                    encryptAlgo = new ShiftMultiplyEncryption();
                    isAlgoDefined = true;
                    break;
                case "double":
                    encryptAlgo = new DoubleEncryption(new ShiftUpEncryption());
                    isAlgoDefined = true;
                    break;
                case "xor":
                    encryptAlgo = new XorEncryption();
                    isAlgoDefined = true;
                    break;
                case "repeat":
                    encryptAlgo = new RepeatEncryption(new ShiftUpEncryption(), 5);
                    isAlgoDefined = true;
                    break;
                default:
                    System.out.println("can't find your chosen algorithm. please try again");
                    break;
            }
        }
        return encryptAlgo;
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
