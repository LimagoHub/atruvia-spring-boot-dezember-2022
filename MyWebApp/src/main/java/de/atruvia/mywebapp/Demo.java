package de.atruvia.mywebapp;


import de.atruvia.mywebapp.repositories.PersonenRepository;
import de.atruvia.mywebapp.repositories.entities.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final PersonenRepository repo;


    @PostConstruct
    public void init() {
        PersonEntity person = PersonEntity.builder().id(UUID.randomUUID().toString()).vorname("Jane").nachname("Doe").build();

        repo.save(person);
    }
}
