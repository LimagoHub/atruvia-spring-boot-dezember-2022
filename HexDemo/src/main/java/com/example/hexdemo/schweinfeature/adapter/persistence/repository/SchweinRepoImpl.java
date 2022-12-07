package com.example.hexdemo.schweinfeature.adapter.persistence.repository;

import com.example.hexdemo.schweinfeature.adapter.mapper.SchweinMapper;
import com.example.hexdemo.schweinfeature.adapter.persistence.raw.crudrepo.SchweinRawRepository;
import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;
import com.example.hexdemo.schweinfeature.domain.schwein.repository.SchweinRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SchweinRepoImpl implements SchweinRepo {

    private final SchweinRawRepository rawRepo;
    private final SchweinMapper mapper;

    @Override
    public Schwein save(Schwein schwein) {
        return mapper.convert(rawRepo.save(mapper.convert(schwein)));
    }

    @Override
    public boolean existsById(String id) {
        return rawRepo.existsById(id);
    }

    @Override
    public Optional<Schwein> findById(String id) {
        return rawRepo.findById(id).map(mapper::convert);
    }

    @Override
    public Iterable<Schwein> findAll() {
        return mapper.convert(rawRepo.findAll());
    }

    @Override
    public boolean deleteById(String id) {
        boolean exists = rawRepo.existsById(id);
        rawRepo.deleteById(id);
        return exists;
    }
}
