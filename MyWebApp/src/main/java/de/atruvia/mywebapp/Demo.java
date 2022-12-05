package de.atruvia.mywebapp;


import de.atruvia.mywebapp.repositories.PersonenRepository;
import de.atruvia.mywebapp.repositories.entities.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Transactional
public class Demo {

    private final PersonenRepository repo;


    private String message = "Hallo Welt";

//    public Demo(@Value("${Demo.message}") String message) {
//        this.message = message;
//        System.out.println(message);
//    }

    @PostConstruct
    public void init() {
        PersonEntity person = PersonEntity.builder().id(UUID.randomUUID().toString()).vorname("Jane").nachname("Doe").build();

        repo.peter(person);
        System.out.println(message);

    }
}
