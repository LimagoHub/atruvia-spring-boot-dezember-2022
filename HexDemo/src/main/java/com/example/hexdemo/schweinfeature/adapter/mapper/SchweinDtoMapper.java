package com.example.hexdemo.schweinfeature.adapter.mapper;


import com.example.hexdemo.schweinfeature.adapter.dto.SchweinDTO;
import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {

    Schwein convert(SchweinDTO schweinDto);
    SchweinDTO convert(Schwein schwein);

    Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
}
