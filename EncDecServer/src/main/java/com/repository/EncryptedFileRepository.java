package com.repository;

import com.models.EncryptedFile;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EncryptedFileRepository extends MongoRepository<EncryptedFile, String> {

    Optional<EncryptedFile> findTopByUsernameAndPathOrderByTimestampDesc(String username, String path);
    
    Boolean existsByUsernameAndPath(String username, String path);

    @DeleteQuery
    void deleteById(String id);
}
