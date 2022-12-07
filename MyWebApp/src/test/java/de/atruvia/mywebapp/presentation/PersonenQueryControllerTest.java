package de.atruvia.mywebapp.presentation;

import de.atruvia.mywebapp.domain.PersonenService;
import de.atruvia.mywebapp.domain.model.Person;
import de.atruvia.mywebapp.presentation.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql({"/create.sql", "/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonenQueryControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService serviceMock;

    private final String validUUID = "b2e24e74-8686-43ea-baff-d9396b4202e0";
    private final PersonDTO validPersonDTO = PersonDTO.builder().id(validUUID).vorname("John").nachname("Doe").build();

    @Test
    void test_1() throws Exception{

        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);

        PersonDTO personDTO = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);
        assertEquals("John",personDTO.getVorname());
    }
    @Test
    void test_2() throws Exception{

        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());
        when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);

        String personDTO = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(personDTO);
    }

    @Test
    void test3() throws Exception {

        Optional<Person> optionalPerson = Optional.of(Person.builder().id("1").vorname("John").nachname("Doe").build());

        when(serviceMock.findeNachId(anyString())).thenReturn(optionalPerson);

        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        PersonDTO personDTO = entity.getBody();

        assertEquals("John",personDTO.getVorname());
        assertEquals(HttpStatus.OK,entity.getStatusCode());
    }

    @Test
    void test4() throws Exception {

        Iterable<Person> personen = List.of(Person.builder().id("1").vorname("John").nachname("Doe").build(),Person.builder().id("2").vorname("John").nachname("Rambo").build());
        when(serviceMock.findeAlle()).thenReturn(personen);

        ResponseEntity<List<PersonDTO>> entity = restTemplate.exchange("/v1/personen", HttpMethod.GET,null,new ParameterizedTypeReference<List<PersonDTO>>() { });

        assertEquals(HttpStatus.OK,entity.getStatusCode());
        //assertEquals(2, StreamSupport.stream(entity.getBody().spliterator(), false).count());

        assertEquals(2,entity.getBody().size());

        entity.getBody().forEach(p->assertEquals("John",p.getVorname()));
    }

}