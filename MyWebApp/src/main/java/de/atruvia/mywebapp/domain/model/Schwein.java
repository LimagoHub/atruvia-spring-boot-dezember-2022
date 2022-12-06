package de.atruvia.mywebapp.domain.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein {

    @Setter(AccessLevel.PRIVATE)
    private String id;

    @Setter(AccessLevel.PRIVATE)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    private int gewicht;

    public void taufen(String name) {
        setName(name);
    }

    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }
}
