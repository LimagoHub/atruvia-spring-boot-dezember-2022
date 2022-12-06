package de.atruvia.mywebapp.domain.mapper;

import de.atruvia.mywebapp.domain.model.Schwein;
import de.atruvia.mywebapp.repositories.entities.SchweinEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SchweinMapper {

    Schwein convert(SchweinEntity schweinEntity);
    SchweinEntity convert(Schwein schwein);

    Iterable<Schwein> convert(Iterable<SchweinEntity> entities);
}
