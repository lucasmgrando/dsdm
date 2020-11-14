package com.example.demo.controller;

import com.example.demo.service.LocalidadService;
import com.example.demo.utils.BadRequestAlertException;
import com.example.demo.service.dto.LocalidadDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
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
public class LocalidadController {

    private final Logger log = LoggerFactory.getLogger(LocalidadController.class);

    private static final String ENTITY_NAME = "Localidad";

    private String applicationName = "Practico";

    private final LocalidadService localidadService;

    public LocalidadController(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    @PostMapping("/localidad")
    public ResponseEntity<LocalidadDTO> createLocalidad(@Valid @RequestBody LocalidadDTO localidadDTO) throws URISyntaxException {
        log.debug("REST request to save Localidad : {}", localidadDTO);
        if (localidadDTO.getId() != null) {
            throw new BadRequestAlertException("A new localidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocalidadDTO result = localidadService.save(localidadDTO);
        return ResponseEntity.created(new URI("/api/localidad/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/localidad")
    public ResponseEntity<LocalidadDTO> updateLocalidad(@Valid @RequestBody LocalidadDTO localidadDTO) throws URISyntaxException {
        log.debug("REST request to update Localidad : {}", localidadDTO);
        if (localidadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocalidadDTO result =localidadService.save(localidadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localidadDTO.getId().toString()))
            .body(result);
    }
	
	/*
    @GetMapping("/localidad")
    public ResponseEntity<List<LocalidadDTO>> getAllLocalidads(Pageable pageable) {
        log.debug("REST request to get a page of Localidads");
        Page<LocalidadDTO> page =localidadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	*/

    @GetMapping("/localidad/{id}")
    public ResponseEntity<LocalidadDTO> getLocalidad(@PathVariable Long id) {
        log.debug("REST request to get Localidad : {}", id);
        Optional<LocalidadDTO> localidadDTO =localidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localidadDTO);
    }

    @DeleteMapping("/localidad/{id}")
    public ResponseEntity<Void> deleteLocalidad(@PathVariable Long id) {
        log.debug("REST request to delete Localidad : {}", id);
        localidadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

