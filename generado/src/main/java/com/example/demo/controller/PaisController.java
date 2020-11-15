package com.example.demo.controller;

import com.example.demo.service.PaisService;
import com.example.demo.utils.BadRequestAlertException;
import com.example.demo.service.dto.PaisDTO;

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
public class PaisController {

    private final Logger log = LoggerFactory.getLogger(PaisController.class);

    private static final String ENTITY_NAME = "Pais";

    private String applicationName = "Practico";

    private final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @PostMapping("/pais")
    public ResponseEntity<PaisDTO> createPais(@Valid @RequestBody PaisDTO paisDTO) throws URISyntaxException {
        log.debug("REST request to save Pais : {}", paisDTO);
        if (paisDTO.getId() != null) {
            throw new BadRequestAlertException("A new pais cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaisDTO result = paisService.save(paisDTO);
        return ResponseEntity.created(new URI("/api/pais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/pais")
    public ResponseEntity<PaisDTO> updatePais(@Valid @RequestBody PaisDTO paisDTO) throws URISyntaxException {
        log.debug("REST request to update Pais : {}", paisDTO);
        if (paisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaisDTO result =paisService.save(paisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paisDTO.getId().toString()))
            .body(result);
    }
	
	/*
    @GetMapping("/pais")
    public ResponseEntity<List<PaisDTO>> getAllPaiss(Pageable pageable) {
        log.debug("REST request to get a page of Paiss");
        Page<PaisDTO> page =paisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	*/

    @GetMapping("/pais/{id}")
    public ResponseEntity<PaisDTO> getPais(@PathVariable Long id) {
        log.debug("REST request to get Pais : {}", id);
        Optional<PaisDTO> paisDTO =paisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paisDTO);
    }

    @DeleteMapping("/pais/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        log.debug("REST request to delete Pais : {}", id);
        paisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

