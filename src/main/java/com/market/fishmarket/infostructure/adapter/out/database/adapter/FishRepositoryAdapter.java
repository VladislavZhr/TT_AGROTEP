package com.market.fishmarket.infostructure.adapter.out.database.adapter;

import com.market.fishmarket.application.port.out.FishRepositoryPort;
import com.market.fishmarket.domain.Fish;
import com.market.fishmarket.infostructure.adapter.out.database.entity.FishEntity;
import com.market.fishmarket.infostructure.adapter.out.database.mapper.FishMapper;
import com.market.fishmarket.infostructure.adapter.out.database.repository.FishRepository;
import com.market.fishmarket.infostructure.exception.errors.FishNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FishRepositoryAdapter implements FishRepositoryPort {

    private final FishRepository repository;

    @Override
    public List<Fish> findAll() {
        List<FishEntity> entities = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return entities.stream()
                .map(FishMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Fish fish) {
        repository.save(FishMapper.toEntity(fish));
    }

    @Override
    public void deleteById(int id) {
        repository.delete(getFishById(id));
    }

    @Override
    public Fish findById(int id) {
        return FishMapper.toDomain(getFishById(id));
    }

    private FishEntity getFishById(int id){
        return repository.findById(id)
                .orElseThrow(()-> new FishNotFoundException("Fish not found by id: " + id));
    }
}
