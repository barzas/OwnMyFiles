package com.security.services;

import com.models.EncryptedFile;
import com.repository.EncryptedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EncryptedFileDetailsServiceImpl {
    @Autowired
    EncryptedFileRepository encryptedFileRepository;

    @Transactional
    public EncryptedFile loadUserByUsername(String username, String path) throws UsernameNotFoundException {
        EncryptedFile encryptedFile = encryptedFileRepository.findByUsernameAndPath(username, path)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return encryptedFile;
    }

    // how its in the example:
//    @Transactional
//    public EncryptedFileImpl loadUserByUsername(String username, String path) throws UsernameNotFoundException {
//        EncryptedFile encryptedFile = encryptedFileRepository.findByUsernameAndPath(username, path)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//        return EncryptedFileImpl.build(encryptedFile);
//    }
}
