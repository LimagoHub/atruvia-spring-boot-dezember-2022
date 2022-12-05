package de.atruvia.mywebapp.repositories.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TinyPerson {

    private final String id;
    private final String nachname;
}
