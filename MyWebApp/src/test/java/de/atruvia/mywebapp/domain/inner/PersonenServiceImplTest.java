package de.atruvia.mywebapp.domain.inner;


import de.atruvia.mywebapp.domain.PersonenServiceException;
import de.atruvia.mywebapp.domain.mapper.PersonMapper;
import de.atruvia.mywebapp.domain.model.Person;
import de.atruvia.mywebapp.repositories.PersonenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock
    private PersonenRepository repoMock;

    @Mock
    private PersonMapper mapperMock;

    @Mock
    private List<String> antipathenMock;

    @Test
    @DisplayName("speichern mit leerem Parameter erwartet eine throws_PersonenServiceException")
    void speichernParameterNull() throws Exception {
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Parameter darf nicht null sein", ex.getMessage());
    }
    @Test
    void speichern__vorname_is_null__throws_PersonenServiceException() throws Exception {
        final Person doe = Person.builder().id(null).vorname(null).nachname("Doe").build();
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(doe));
        assertEquals("Vorname zu kurz", ex.getMessage());
    }
    @Test
    void speichern__vorname_is_to_short__throws_PersonenServiceException() throws Exception {
        final Person doe = Person.builder().id(null).vorname("J").nachname("Doe").build();
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(doe));
        assertEquals("Vorname zu kurz", ex.getMessage());
    }

    @Test
    void speichern__nachname_is_null__throws_PersonenServiceException() throws Exception {
        final Person doe = Person.builder().id(null).vorname("John").nachname(null).build();
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(doe));
        assertEquals("Nachname zu kurz", ex.getMessage());
    }
    @Test
    void speichern__nachname_is_to_short__throws_PersonenServiceException() throws Exception {
        final Person doe = Person.builder().id(null).vorname("John").nachname("D").build();
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(doe));
        assertEquals("Nachname zu kurz", ex.getMessage());
    }

    @Test
    void speichern__unerwuenschte_person__throws_PersonenServiceException() throws Exception {
        Mockito.when(antipathenMock.contains(Mockito.anyString())).thenReturn(true);
        final Person dooferTyp = Person.builder().id(null).vorname("John").nachname("Doe").build();
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(dooferTyp));
        assertEquals("Antipath", ex.getMessage());
    }

}