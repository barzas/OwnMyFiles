package com.controllers;

import javax.validation.Valid;

import com.encryptorDecryptor.algorithm.handling.IEncryptionAlgorithm;
import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;
import com.encryptorDecryptor.file.handling.UserAction;
import com.encryptorDecryptor.multithreading.AsyncDirectoryProcessor;
import com.payload.request.DecryptRequest;
import com.payload.request.EncryptRequest;
import com.payload.response.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/action")
public class ActionController {
    
    @PostMapping("/encrypt")
    public ResponseEntity<?> encryptFile(@Valid @RequestBody EncryptRequest encryptRequest) {
        //TODO
        UserAction userAction = new UserAction();
        IEncryptionAlgorithm encryptionAlgorithm = userAction.getAlgo(encryptRequest.getEnctype());
        AsyncDirectoryProcessor asyncDirectoryProcessor = new AsyncDirectoryProcessor(encryptionAlgorithm);

        EncryptionEventObserver asyncObserver = new EncryptionEventObserver();
        asyncDirectoryProcessor.addObserver(asyncObserver);

        try {
            asyncDirectoryProcessor.encryptDirectory(userAction.getPath());
        } catch (InvalidEncryptionAlgorithmTypeException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new MessageResponse("Encrypt file Succesfull"));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decryptFile(@Valid @RequestBody DecryptRequest decryptRequest) {
        //TODO

        return ResponseEntity.ok(new MessageResponse("Decrypt file Succesfull"));
    }
}
