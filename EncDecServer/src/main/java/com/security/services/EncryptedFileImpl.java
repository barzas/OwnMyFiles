package com.security.services;

import com.models.EncryptedFile;

public class EncryptedFileImpl {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String algorithm;

    private String path;

    private int key;

    public EncryptedFileImpl(String id, String username, String algorithm, String path, int key) {
        this.id = id;
        this.username = username;
        this.algorithm = algorithm;
        this.path = path;
        this.key = key;
    }

    public static EncryptedFileImpl build(EncryptedFile encryptedFile) {
        return new EncryptedFileImpl(
                encryptedFile.getId(),
                encryptedFile.getUsername(),
                encryptedFile.getAlgorithm(),
                encryptedFile.getPath(),
                encryptedFile.getKey());
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getPath() {
        return path;
    }

    public int getKey() {
        return key;
    }
}
