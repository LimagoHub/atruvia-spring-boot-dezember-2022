package de.atruvia.mywebapp.domain;

import de.atruvia.mywebapp.domain.model.Person;

import java.util.Optional;

public interface PersonenService {

    void speichern(Person person) throws PersonenServiceException ;
    void aendern(Person person) throws PersonenServiceException ;
    boolean loeschen(String id) throws PersonenServiceException ;
    Optional<Person> findeNachId(String id) throws PersonenServiceException ;
    Iterable<Person> findeAlle() throws PersonenServiceException;
}
