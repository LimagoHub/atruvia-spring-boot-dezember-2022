package de.atruvia.mywebapp.presentation;

import de.atruvia.mywebapp.domain.PersonenService;
import de.atruvia.mywebapp.domain.PersonenServiceException;
import de.atruvia.mywebapp.presentation.dto.PersonDTO;
import de.atruvia.mywebapp.presentation.mapper.PersonDtoMapper;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/personen")
@RequiredArgsConstructor
public class PersonenCommandController {

    private final PersonenService service;
    private final PersonDtoMapper mapper;

    @DeleteMapping()
    public ResponseEntity<Void> delete(@PathVariable String id) throws PersonenServiceException {
        if(service.loeschen(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createIdempotent(@Valid @RequestBody PersonDTO person, UriComponentsBuilder builder)throws PersonenServiceException  {
        service.speichern(mapper.convert(person));
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
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody PersonDTO person) throws PersonenServiceException {

        service.aendern(mapper.convert(person));

        return ResponseEntity.ok().build();
    }

//    @PatchMapping(value = "/andererKram/action")
//    public ResponseEntity<Void> patch(@PathVariable String id,  String neuerVorname) {
//
//
//        System.out.printf("Vorname wird geaendert\n");
//
//        return ResponseEntity.ok().build();
//    }
}
