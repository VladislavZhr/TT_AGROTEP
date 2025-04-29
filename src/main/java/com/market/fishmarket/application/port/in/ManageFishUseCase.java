package com.market.fishmarket.application.port.in;

import com.market.fishmarket.domain.Fish;

import java.math.BigDecimal;
import java.util.List;

public interface ManageFishUseCase {
    Fish getNewFish();
    void saveFishInDb(String name, BigDecimal price, List<String> savedFileNames);
    void deleteFish(int id);
    List<Fish> getSortedFishList();
}
