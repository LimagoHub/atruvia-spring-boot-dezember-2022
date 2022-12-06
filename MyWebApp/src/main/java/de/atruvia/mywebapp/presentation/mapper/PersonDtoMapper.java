package de.atruvia.mywebapp.presentation.mapper;


import de.atruvia.mywebapp.domain.model.Person;
import de.atruvia.mywebapp.presentation.dto.PersonDTO;
import de.atruvia.mywebapp.repositories.entities.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {
    PersonDTO convert(Person person);
    Person convert(PersonDTO dto);
    Iterable<PersonDTO> convert(Iterable<Person> personen);
}
