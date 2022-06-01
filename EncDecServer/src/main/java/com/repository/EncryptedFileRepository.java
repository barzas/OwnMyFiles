package com.repository;

import com.models.EncryptedFile;
import com.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EncryptedFileRepository extends MongoRepository<EncryptedFile, String> {

    Optional<EncryptedFile> findByUsernameAndPath(String username, String path);

    Boolean existsByUsernameAndPath(String username, String path);
}
