package com.market.fishmarket.infostructure.adapter.in.mapper;

import com.market.fishmarket.domain.Fish;
import com.market.fishmarket.infostructure.adapter.in.dto.FishDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FishMapper {

    public static FishDTO toFishDTO(Fish fish) {
        return new FishDTO();
    }

    public static Fish toFish(FishDTO fishDTO) {
        return Fish.builder()
                .name(fishDTO.getName())
                .price(fishDTO.getPrice())
                .imageFilesNames(getFileNames(fishDTO.getImageFiles()))
                .build();
    }

    private static List<String> getFileNames(List<MultipartFile> imageFiles) {
        return imageFiles.stream()
                .map(MultipartFile::getOriginalFilename)
                .toList();

    }

}
