package com.models;

import org.apache.tomcat.jni.Time;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "encryptedFiles")
public class EncryptedFile {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String algorithm;

    @NotBlank
    @Size(max = 50)
    private String path;

    @NotBlank
    private int key;

    @NotBlank
    private int secondKey;

    @NotBlank
    private LocalTime timestamp;

    public EncryptedFile(String username, String algorithm, String path, int key, int secondKey) {
        this.username = username;
        this.algorithm = algorithm;
        this.path = path;
        this.key = key;
        this.secondKey = secondKey;
        this.timestamp = LocalTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getSecondKey() {
        return secondKey;
    }

    public void setSecondKey(int secondKey) {
        this.secondKey = secondKey;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }
}
