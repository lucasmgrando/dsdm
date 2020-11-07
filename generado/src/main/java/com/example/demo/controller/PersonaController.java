package com.example.demo.controller;

import com.example.demo.service.PersonaService;
import BadRequestAlertException;
import com.example.demo.dto.PersonaDTO;

import HeaderUtil;
import PaginationUtil;
import ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonaController {

    private final Logger log = LoggerFactory.getLogger(PersonaController.class);

    private static final String ENTITY_NAME = "Persona";

    @Value("${applicationName}")
    private String applicationName;

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/persona")
    public ResponseEntity<PersonaDTO> createPersona(@Valid @RequestBody personaDTO personaDTO) throws URISyntaxException {
        log.debug("REST request to save Persona : {}", personaDTO);
        if (personaDTO.getId() != null) {
            throw new BadRequestAlertException("A new persona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonaDTO result = personaService.save(personaDTO);
        return ResponseEntity.created(new URI("/api/persona/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/persona")
    public ResponseEntity<PersonaDTO> updatePersona(@Valid @RequestBody PersonaDTO personaDTO) throws URISyntaxException {
        log.debug("REST request to update Persona : {}", personaDTO);
        if (personaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonaDTO result =personaService.save(personaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personaDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/persona")
    public ResponseEntity<List<PersonaDTO>> getAllPersonas(Pageable pageable) {
        log.debug("REST request to get a page of Personas");
        Page<PersonaDTO> page =personaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/persona/{id}")
    public ResponseEntity<PersonaDTO> getPersona(@PathVariable Long id) {
        log.debug("REST request to get Persona : {}", id);
        Optional<PersonaDTO> personaDTO =personaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personaDTO);
    }

    @DeleteMapping("/persona/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        log.debug("REST request to delete Persona : {}", id);
        personaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

