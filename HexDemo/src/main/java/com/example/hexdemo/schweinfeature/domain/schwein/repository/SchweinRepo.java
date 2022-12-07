package com.example.hexdemo.schweinfeature.domain.schwein.repository;

import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;

import java.util.Optional;

public interface SchweinRepo {

    Schwein save(Schwein schwein);
    boolean existsById(String id);

    Optional<Schwein> findById(String id);

    Iterable<Schwein> findAll();

    boolean deleteById(String id);

}
