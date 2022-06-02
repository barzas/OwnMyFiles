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
import com.security.services.EncryptedFileDetailsServiceImpl;
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

    @Autowired
    private EncryptedFileDetailsServiceImpl encryptedFileService;

    private UserAction userAction = new UserAction();

    @PostMapping("/encrypt")
    public ResponseEntity<?> encryptFile(@Valid @RequestBody EncryptRequest encryptRequest) {
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

        EncryptedFile encryptedFile= new EncryptedFile(encryptRequest.getUsername(), encryptRequest.getEnctype(), encryptRequest.getPath(), key);
        encryptedFileRepository.save(encryptedFile);
        return ResponseEntity.ok(new MessageResponse("Encrypt file Successful"));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decryptFile(@Valid @RequestBody DecryptRequest decryptRequest) {

        EncryptedFile encryptedFile = encryptedFileService.loadUserByUsernameAndPath(decryptRequest.getUsername(), decryptRequest.getPath());
        //todo- delete from db
        try {
            IEncryptionAlgorithm encryptAlgo = userAction.getAlgo(encryptedFile.getAlgorithm());
            AsyncDirectoryProcessor asyncDirectoryProcessor = new AsyncDirectoryProcessor(encryptAlgo, encryptedFile.getKey());
            EncryptionEventObserver asyncObserver = new EncryptionEventObserver();
            asyncDirectoryProcessor.addObserver(asyncObserver);

            asyncDirectoryProcessor.decryptDirectory(encryptedFile.getPath());

        } catch (InvalidEncryptionAlgorithmTypeException | IOException | InterruptedException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Decrypt file Successful"));
    }
}
