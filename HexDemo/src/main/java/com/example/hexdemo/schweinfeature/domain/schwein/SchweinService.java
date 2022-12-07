package com.example.hexdemo.schweinfeature.domain.schwein;


import com.example.hexdemo.schweinfeature.domain.schwein.model.Schwein;

import java.util.Optional;

public interface SchweinService {

    void speichern(Schwein person) throws SchweineServiceException;
    void aendern(Schwein person) throws SchweineServiceException;
    boolean loeschen(String id) throws SchweineServiceException;
    Optional<Schwein> findeNachId(String id) throws SchweineServiceException;
    Iterable<Schwein> findeAlle() throws SchweineServiceException;

    boolean fuettern(String id) throws SchweineServiceException;
}
