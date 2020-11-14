package com.example.demo.controller;

import com.example.demo.service.ProvinciaService;
import com.example.demo.utils.BadRequestAlertException;
import com.example.demo.service.dto.ProvinciaDTO;

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
public class ProvinciaController {

    private final Logger log = LoggerFactory.getLogger(ProvinciaController.class);

    private static final String ENTITY_NAME = "Provincia";

    private String applicationName = "Practico";

    private final ProvinciaService provinciaService;

    public ProvinciaController(ProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    @PostMapping("/provincia")
    public ResponseEntity<ProvinciaDTO> createProvincia(@Valid @RequestBody ProvinciaDTO provinciaDTO) throws URISyntaxException {
        log.debug("REST request to save Provincia : {}", provinciaDTO);
        if (provinciaDTO.getId() != null) {
            throw new BadRequestAlertException("A new provincia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvinciaDTO result = provinciaService.save(provinciaDTO);
        return ResponseEntity.created(new URI("/api/provincia/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/provincia")
    public ResponseEntity<ProvinciaDTO> updateProvincia(@Valid @RequestBody ProvinciaDTO provinciaDTO) throws URISyntaxException {
        log.debug("REST request to update Provincia : {}", provinciaDTO);
        if (provinciaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProvinciaDTO result =provinciaService.save(provinciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, provinciaDTO.getId().toString()))
            .body(result);
    }
	
	/*
    @GetMapping("/provincia")
    public ResponseEntity<List<ProvinciaDTO>> getAllProvincias(Pageable pageable) {
        log.debug("REST request to get a page of Provincias");
        Page<ProvinciaDTO> page =provinciaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	*/

    @GetMapping("/provincia/{id}")
    public ResponseEntity<ProvinciaDTO> getProvincia(@PathVariable Long id) {
        log.debug("REST request to get Provincia : {}", id);
        Optional<ProvinciaDTO> provinciaDTO =provinciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(provinciaDTO);
    }

    @DeleteMapping("/provincia/{id}")
    public ResponseEntity<Void> deleteProvincia(@PathVariable Long id) {
        log.debug("REST request to delete Provincia : {}", id);
        provinciaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

