package com.market.fishmarket.infostructure.adapter.in.mapper;

import com.market.fishmarket.application.port.in.FileStorageUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.market.fishmarket.infostructure.adapter.in.adapter.MultipartFileAdapter;

import java.util.Collections;
import java.util.List;

@Component
public class WebFishMapper {

    private final FileStorageUseCase fileStorageUseCase;

    public WebFishMapper(FileStorageUseCase fileStorageUseCase) {
        this.fileStorageUseCase = fileStorageUseCase;
    }

    public List<String> mapMultipartFilesToFileNames(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }

        return files.stream()
                .map(MultipartFileAdapter::new)
                .map(fileStorageUseCase::saveFile)
                .toList();
    }
}

