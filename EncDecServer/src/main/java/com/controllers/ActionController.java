package com.controllers;

import javax.validation.Valid;

import com.payload.request.DecryptRequest;
import com.payload.request.EncryptRequest;
import com.payload.response.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/action")
public class ActionController {
    
    @PostMapping("/encrypt")
    public ResponseEntity<?> encryptFile(@RequestBody EncryptRequest encryptRequest) {
        //TODO
        return ResponseEntity.ok(new MessageResponse("Encrypt file Succesfull"));
    }

    @PostMapping("/decrypt")
    public ResponseEntity<?> decryptFile(@Valid @RequestBody DecryptRequest decryptRequest) {
        //TODO
        return ResponseEntity.ok(new MessageResponse("Decrypt file Succesfull"));
    }
}
