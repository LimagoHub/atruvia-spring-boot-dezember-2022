package de.atruvia.mywebapp.domain.mapper;


import de.atruvia.mywebapp.domain.model.Person;
import de.atruvia.mywebapp.repositories.entities.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person convert(PersonEntity entity);
    PersonEntity convert(Person entity);
    Iterable<Person> convert(Iterable<PersonEntity> entities);
}
