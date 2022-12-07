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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PersonenCommandControllerTest {

    private final String validUUID = "b2e24e74-8686-43ea-baff-d9396b4202e0";
    private final PersonDTO validPersonDTO = PersonDTO.builder().id(validUUID).vorname("John").nachname("Doe").build();

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService personenServiceMock;

    @Test
    void test_loeschen_1() throws Exception{
        when(personenServiceMock.loeschen(anyString())).thenReturn(true);

        ResponseEntity<Void> entity = restTemplate.exchange("/v1/personen/4711", HttpMethod.DELETE,null,Void.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());


    }

    @Test
    void test_loeschen_2() throws Exception{
        when(personenServiceMock.loeschen(anyString())).thenReturn(false);

        ResponseEntity<Void> entity = restTemplate.exchange("/v1/personen/4711", HttpMethod.DELETE,null,Void.class);

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());


    }

    @Test
    void test_speichern_1() throws Exception{

        // Arrange
        final Person validPerson= Person.builder().id(validUUID).vorname("John").nachname("Doe").build();

        //doNothing().when(personenServiceMock).speichern(any());

        HttpEntity requestEntity = new HttpEntity(validPersonDTO);

        ResponseEntity<Void> entity = restTemplate.exchange("/v1/personen", HttpMethod.POST,requestEntity,Void.class);

        verify(personenServiceMock, times(1)).speichern(validPerson);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertTrue(entity.getHeaders().get("location").get(0).endsWith("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0"));

    }


}