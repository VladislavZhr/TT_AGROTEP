package com.market.fishmarket.infostructure.adapter.in.adapter;

import com.market.fishmarket.application.port.out.FileStoragePort;
import com.market.fishmarket.infostructure.exception.errors.FileReadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class MultipartFileAdapter implements FileStoragePort {

    private final MultipartFile multipartFile;

    public MultipartFileAdapter(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String getOriginalFilename() {
        return multipartFile.getOriginalFilename();
    }

    @Override
    public InputStream getInputStream() {
        try {
            return multipartFile.getInputStream();
        } catch (Exception e) {
            throw new FileReadException("Cannot read file input stream");
        }
    }
}
