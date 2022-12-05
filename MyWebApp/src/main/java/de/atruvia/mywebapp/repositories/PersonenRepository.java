package de.atruvia.mywebapp.repositories;

import de.atruvia.mywebapp.repositories.entities.PersonEntity;
import de.atruvia.mywebapp.repositories.entities.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonenRepository extends CrudRepository<PersonEntity, String>, PersonenCustomRepository {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p where p.nachname = :nachname")
    Iterable<PersonEntity> xyz(String nachname);

    @Query("select p.vorname, p.nachname from PersonEntity p")
    Iterable<Object[]> abc();

    @Query("select new de.atruvia.mywebapp.repositories.entities.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> bcd();

}
