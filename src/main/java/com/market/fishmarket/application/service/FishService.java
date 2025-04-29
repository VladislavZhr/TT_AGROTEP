package com.market.fishmarket.application.service;

import com.market.fishmarket.application.port.in.ManageFishUseCase;
import com.market.fishmarket.application.port.out.FishRepositoryPort;
import com.market.fishmarket.domain.Fish;
import com.market.fishmarket.infostructure.exception.errors.FileDeletionException;
import com.market.fishmarket.infostructure.exception.errors.InvalidImageFilesException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class FishService implements ManageFishUseCase {

    private final FishRepositoryPort fishRepository;

    @Override
    public List<Fish> getSortedFishList(){
        return fishRepository.findAll();
    }

    @Override
    public Fish getNewFish() {
        return new Fish();
    }

    @Override
    public void saveFishInDb(String name, BigDecimal price, List<String> savedFileNames){
        Fish fish = Fish.builder()
                .name(name)
                .price(price)
                .catchDate(new Date())
                .imageFilesNames(savedFileNames)
                .build();
        fishRepository.save(fish);
    }

    @Override
    public void deleteFish(int id){
        Fish fish = getFishById(id);
        deleteImages(fish.getImageFilesNames());
        fishRepository.deleteById(id);
    }

    private void deleteImages(List<String> imageFilesNames){
        validateImageFilesNames(imageFilesNames);
        for (String fileName : imageFilesNames) {
            try {
                Path imagePath = Paths.get("public/images/" + fileName);
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                throw new FileDeletionException("Failed to delete image file: " + fileName);
            }
        }
    }

    private void validateImageFilesNames(List<String> imageFilesNames) {
        if (imageFilesNames == null || imageFilesNames.isEmpty()) {
            throw new InvalidImageFilesException("imageFilesNames cannot be null or empty");
        }
    }

    private Fish getFishById(int id){
        return fishRepository.findById(id);
    }

}
