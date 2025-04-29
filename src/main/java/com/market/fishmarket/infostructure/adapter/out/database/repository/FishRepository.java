package com.market.fishmarket.infostructure.adapter.out.database.repository;

import com.market.fishmarket.infostructure.adapter.out.database.entity.FishEntity;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FishRepository extends JpaRepository<FishEntity, Integer> {
    Optional<FishEntity> findById(@Nullable int id);
}
