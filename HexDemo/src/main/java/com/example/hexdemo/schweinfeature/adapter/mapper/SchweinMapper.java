package com.example.hexdemo.schweinfeature.adapter.mapper;


import com.example.hexdemo.schweinfeature.adapter.persistence.raw.entity.SchweinEntity;
import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity schweinEntity);
    SchweinEntity convert(Schwein schwein);

    Iterable<Schwein> convert(Iterable<SchweinEntity> entities);
}
