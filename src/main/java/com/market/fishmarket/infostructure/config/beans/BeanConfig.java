package com.market.fishmarket.infostructure.config.beans;

import com.market.fishmarket.application.port.in.FileStorageUseCase;
import com.market.fishmarket.application.port.in.ManageFishUseCase;
import com.market.fishmarket.application.port.out.FishRepositoryPort;
import com.market.fishmarket.application.service.FileService;
import com.market.fishmarket.application.service.FishService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public FileStorageUseCase fileStorageUseCase() {
        return new FileService();
    }

    @Bean
    public ManageFishUseCase manageFishUseCase(FishRepositoryPort fishRepository) {
        return new FishService(fishRepository);
    }


}
