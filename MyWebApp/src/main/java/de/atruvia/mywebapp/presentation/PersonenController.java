package de.atruvia.mywebapp.presentation;

import de.atruvia.mywebapp.presentation.dto.PersonDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {



    private List<PersonDTO> personen = List.of(
            PersonDTO.builder().id("1").vorname("John").nachname("Doe").build(),
            PersonDTO.builder().id("2").vorname("John").nachname("Wayne").build(),
            PersonDTO.builder().id("3").vorname("John").nachname("McClaine").build(),
            PersonDTO.builder().id("4").vorname("John").nachname("Wick").build(),
            PersonDTO.builder().id("5").vorname("John Boy").nachname("Walton").build()
    );

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    @ApiResponse(responseCode = "400", description = "Falsches Format")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDTO> getPerson(@PathVariable  String id) {

        Optional<PersonDTO> personDTOOptional;

        if("123".equals(id)) {
            personDTOOptional = Optional.empty();
        } else {
            personDTOOptional = Optional.of(PersonDTO.builder().id(id).vorname("John").nachname("Doe").build());
        }

        return ResponseEntity.of(personDTOOptional);
    }


    @ApiResponse(responseCode = "200", description = "Liste wurde erstellt")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll(
            @RequestParam(required = false, defaultValue = "") String vorname,
            @RequestParam(required = false, defaultValue = "") String nachname
    ) {
        System.out.printf("Vorname = %s, nachname = %s\n", vorname, nachname);
        return ResponseEntity.ok(personen);
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@PathVariable String id) {
        System.out.printf("Person mit der Id %s wird geloescht\n", id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createIdempotent(@Valid @RequestBody PersonDTO person, UriComponentsBuilder builder) {
        System.out.printf("Person wird gespeichert\n");
        var uri = builder.path("/v1/personen/{id}").buildAndExpand(person.getId());
        return ResponseEntity.created(uri.toUri()).build();
    }

//    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createIdempotent(@RequestBody PersonDTO person, UriComponentsBuilder builder) {
//
//        person.setId(UUID.randomUUID().toString());
//
//        System.out.printf("Person wird gespeichert\n");
//        var uri = builder.path("/v1/personen/{id}").buildAndExpand(person.getId());
//        return ResponseEntity.created(uri.toUri()).build();
//    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody PersonDTO person) {


        System.out.printf("Person wird geaendert\n");

        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/andererKram/action")
    public ResponseEntity<Void> patch(@PathVariable String neuerVorname) {


        System.out.printf("Vorname wird geaendert\n");

        return ResponseEntity.ok().build();
    }
}
