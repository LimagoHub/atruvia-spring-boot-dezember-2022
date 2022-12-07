package com.example.hexdemo.schweinfeature.domain.schwein.model;


import lombok.*;

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
