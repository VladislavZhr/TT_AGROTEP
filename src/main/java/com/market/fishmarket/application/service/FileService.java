package com.market.fishmarket.application.service;

import com.market.fishmarket.application.port.in.FileStorageUseCase;
import com.market.fishmarket.application.port.out.FileStoragePort;
import com.market.fishmarket.infostructure.exception.errors.DirectoryCreationException;
import com.market.fishmarket.infostructure.exception.errors.FileStorageException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileService implements FileStorageUseCase {

    private static final String UPLOAD_DIR = "public/images/";

    @Override
    public String saveFile(FileStoragePort file) {
        Path uploadPath = Paths.get(UPLOAD_DIR);

        ensureDirectoryExists(uploadPath);

        String storageFileName = generateStorageFileName(file.getOriginalFilename());

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, uploadPath.resolve(storageFileName), StandardCopyOption.REPLACE_EXISTING);
            return storageFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Failed to store file: " + storageFileName);
        }
    }

    private void ensureDirectoryExists(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException ex) {
            throw new DirectoryCreationException("Could not create upload directory: " + path);
        }
    }

    private String generateStorageFileName(String originalFilename) {
        return System.currentTimeMillis() + "_" + originalFilename;
    }
}
