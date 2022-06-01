package com.controllers;

import javax.validation.Valid;

import com.encryptorDecryptor.algorithm.handling.IEncryptionAlgorithm;
import com.encryptorDecryptor.encryption.logs.EncryptionEventObserver;
import com.encryptorDecryptor.exceptions.InvalidEncryptionAlgorithmTypeException;
import com.encryptorDecryptor.file.handling.UserAction;
import com.encryptorDecryptor.key.Key;
import com.encryptorDecryptor.multithreading.AsyncDirectoryProcessor;
import com.models.EncryptedFile;
import com.payload.request.DecryptRequest;
import com.payload.request.EncryptRequest;
import com.payload.response.MessageResponse;

import com.repository.EncryptedFileRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    EncryptedFileRepository encryptedFileRepository;

    @PostMapping("/encrypt")
    public ResponseEntity<?> encryptFile(@Valid @RequestBody EncryptRequest encryptRequest) {
        //TODO
        UserAction userAction = new UserAction();
        EncryptionEventObserver asyncObserver = new EncryptionEventObserver();
        int key = Key.generateKey();
        try {
            IEncryptionAlgorithm encryptionAlgorithm = userAction.getAlgo(encryptRequest.getEnctype());
            AsyncDirectoryProcessor asyncDirectoryProcessor = new AsyncDirectoryProcessor(encryptionAlgorithm, key);
            asyncDirectoryProcessor.addObserver(asyncObserver);
            asyncDirectoryProcessor.encryptDirectory(encryptRequest.getPath());
        } catch (InvalidEncryptionAlgorithmTypeException | IOException | InterruptedException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        EncryptedFile encryptedFile= new EncryptedFile(encryptRequest.getUsername(), encryptRequest.getEnctype(), encryptRequest.getPath(), String.valueOf(key));
        encryptedFileRepository.save(encryptedFile);
        return ResponseEntity.ok(new MessageResponse("Encrypt file Succesfull"));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decryptFile(@Valid @RequestBody DecryptRequest decryptRequest) {
        //TODO

        return ResponseEntity.ok(new MessageResponse("Decrypt file Succesfull"));
    }
}
