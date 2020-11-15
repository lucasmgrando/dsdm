package com.example.demo.controller;

import com.example.demo.service.LibroService;
import com.example.demo.utils.BadRequestAlertException;
import com.example.demo.service.dto.LibroDTO;

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
public class LibroController {

    private final Logger log = LoggerFactory.getLogger(LibroController.class);

    private static final String ENTITY_NAME = "Libro";

    private String applicationName = "Practico";

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping("/libro")
    public ResponseEntity<LibroDTO> createLibro(@Valid @RequestBody LibroDTO libroDTO) throws URISyntaxException {
        log.debug("REST request to save Libro : {}", libroDTO);
        if (libroDTO.getId() != null) {
            throw new BadRequestAlertException("A new libro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LibroDTO result = libroService.save(libroDTO);
        return ResponseEntity.created(new URI("/api/libro/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/libro")
    public ResponseEntity<LibroDTO> updateLibro(@Valid @RequestBody LibroDTO libroDTO) throws URISyntaxException {
        log.debug("REST request to update Libro : {}", libroDTO);
        if (libroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LibroDTO result =libroService.save(libroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, libroDTO.getId().toString()))
            .body(result);
    }
	
	/*
    @GetMapping("/libro")
    public ResponseEntity<List<LibroDTO>> getAllLibros(Pageable pageable) {
        log.debug("REST request to get a page of Libros");
        Page<LibroDTO> page =libroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	*/

    @GetMapping("/libro/{id}")
    public ResponseEntity<LibroDTO> getLibro(@PathVariable Long id) {
        log.debug("REST request to get Libro : {}", id);
        Optional<LibroDTO> libroDTO =libroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(libroDTO);
    }

    @DeleteMapping("/libro/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        log.debug("REST request to delete Libro : {}", id);
        libroService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

