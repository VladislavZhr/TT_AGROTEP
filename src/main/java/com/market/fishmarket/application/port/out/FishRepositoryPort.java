package com.market.fishmarket.application.port.out;

import com.market.fishmarket.domain.Fish;

import java.util.List;

public interface FishRepositoryPort {
    void save(Fish fish);
    void deleteById(int id);
    Fish findById(int id);
    List<Fish> findAll();
}
