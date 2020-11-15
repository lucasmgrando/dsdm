package com.example.demo.controller;

import com.example.demo.service.ZonaService;
import com.example.demo.utils.BadRequestAlertException;
import com.example.demo.service.dto.ZonaDTO;

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
public class ZonaController {

    private final Logger log = LoggerFactory.getLogger(ZonaController.class);

    private static final String ENTITY_NAME = "Zona";

    private String applicationName = "Practico";

    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService) {
        this.zonaService = zonaService;
    }

    @PostMapping("/zona")
    public ResponseEntity<ZonaDTO> createZona(@Valid @RequestBody ZonaDTO zonaDTO) throws URISyntaxException {
        log.debug("REST request to save Zona : {}", zonaDTO);
        if (zonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new zona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ZonaDTO result = zonaService.save(zonaDTO);
        return ResponseEntity.created(new URI("/api/zona/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/zona")
    public ResponseEntity<ZonaDTO> updateZona(@Valid @RequestBody ZonaDTO zonaDTO) throws URISyntaxException {
        log.debug("REST request to update Zona : {}", zonaDTO);
        if (zonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ZonaDTO result =zonaService.save(zonaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, zonaDTO.getId().toString()))
            .body(result);
    }
	
	/*
    @GetMapping("/zona")
    public ResponseEntity<List<ZonaDTO>> getAllZonas(Pageable pageable) {
        log.debug("REST request to get a page of Zonas");
        Page<ZonaDTO> page =zonaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	*/

    @GetMapping("/zona/{id}")
    public ResponseEntity<ZonaDTO> getZona(@PathVariable Long id) {
        log.debug("REST request to get Zona : {}", id);
        Optional<ZonaDTO> zonaDTO =zonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(zonaDTO);
    }

    @DeleteMapping("/zona/{id}")
    public ResponseEntity<Void> deleteZona(@PathVariable Long id) {
        log.debug("REST request to delete Zona : {}", id);
        zonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

