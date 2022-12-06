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
public class PersonenQueryController {

    private final PersonenService service;
    private final PersonDtoMapper mapper;

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
    public ResponseEntity<PersonDTO> getPerson(@PathVariable  String id) throws PersonenServiceException {

       return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));
    }


    @ApiResponse(responseCode = "200", description = "Liste wurde erstellt")
    @ApiResponse(responseCode = "500", description = "interner Serverfehler")
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> findAll(
            @RequestParam(required = false, defaultValue = "") String vorname,
            @RequestParam(required = false, defaultValue = "") String nachname
    ) throws PersonenServiceException {

        return ResponseEntity.ok(mapper.convert(service.findeAlle()));
    }

}
