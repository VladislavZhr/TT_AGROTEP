package com.market.fishmarket.infostructure.adapter.out.database.mapper;

import com.market.fishmarket.domain.Fish;
import com.market.fishmarket.infostructure.adapter.out.database.entity.FishEntity;

public class FishMapper {

    public static Fish toDomain(FishEntity entity){
        return Fish.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .catchDate(entity.getCatchDate())
                .imageFilesNames(entity.getImageFilesNames())
                .build();
    }

    public static FishEntity toEntity(Fish fish){
        return FishEntity.builder()
                .name(fish.getName())
                .price(fish.getPrice())
                .catchDate(fish.getCatchDate())
                .imageFilesNames(fish.getImageFilesNames())
                .build();
    }
}
