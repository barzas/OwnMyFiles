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
    public EncryptedFile loadTopUsernameAndPathOrderByTimestampDesc(String username, String path) throws UsernameNotFoundException {
        EncryptedFile encryptedFile = encryptedFileRepository.findTopByUsernameAndPathOrderByTimestampDesc(username, path)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return encryptedFile;
    }

    public void deleteById(String id) {
        encryptedFileRepository.deleteById(id);
    }
}
