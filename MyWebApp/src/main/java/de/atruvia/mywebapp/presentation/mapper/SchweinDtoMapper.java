package de.atruvia.mywebapp.presentation.mapper;

import de.atruvia.mywebapp.domain.model.Schwein;
import de.atruvia.mywebapp.presentation.dto.SchweinDTO;
import de.atruvia.mywebapp.repositories.entities.SchweinEntity;

public interface SchweinDtoMapper {

    Schwein convert(SchweinDTO schweinDto);
    SchweinDTO convert(Schwein schwein);

    Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);
}
