package com.example.demo.controller;

import com.example.demo.service.DomicilioService;
import com.example.demo.utils.BadRequestAlertException;
import com.example.demo.service.dto.DomicilioDTO;

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
public class DomicilioController {

    private final Logger log = LoggerFactory.getLogger(DomicilioController.class);

    private static final String ENTITY_NAME = "Domicilio";

    private String applicationName = "Practico";

    private final DomicilioService domicilioService;

    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @PostMapping("/domicilio")
    public ResponseEntity<DomicilioDTO> createDomicilio(@Valid @RequestBody DomicilioDTO domicilioDTO) throws URISyntaxException {
        log.debug("REST request to save Domicilio : {}", domicilioDTO);
        if (domicilioDTO.getId() != null) {
            throw new BadRequestAlertException("A new domicilio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DomicilioDTO result = domicilioService.save(domicilioDTO);
        return ResponseEntity.created(new URI("/api/domicilio/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/domicilio")
    public ResponseEntity<DomicilioDTO> updateDomicilio(@Valid @RequestBody DomicilioDTO domicilioDTO) throws URISyntaxException {
        log.debug("REST request to update Domicilio : {}", domicilioDTO);
        if (domicilioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DomicilioDTO result =domicilioService.save(domicilioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, domicilioDTO.getId().toString()))
            .body(result);
    }
	
	/*
    @GetMapping("/domicilio")
    public ResponseEntity<List<DomicilioDTO>> getAllDomicilios(Pageable pageable) {
        log.debug("REST request to get a page of Domicilios");
        Page<DomicilioDTO> page =domicilioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	*/

    @GetMapping("/domicilio/{id}")
    public ResponseEntity<DomicilioDTO> getDomicilio(@PathVariable Long id) {
        log.debug("REST request to get Domicilio : {}", id);
        Optional<DomicilioDTO> domicilioDTO =domicilioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(domicilioDTO);
    }

    @DeleteMapping("/domicilio/{id}")
    public ResponseEntity<Void> deleteDomicilio(@PathVariable Long id) {
        log.debug("REST request to delete Domicilio : {}", id);
        domicilioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

