package com.example.demo.controller;

import com.example.demo.service.AutorService;
import com.example.demo.utils.BadRequestAlertException;
import com.example.demo.service.dto.AutorDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

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
public class AutorController {

    private final Logger log = LoggerFactory.getLogger(AutorController.class);

    private static final String ENTITY_NAME = "Autor";

    private String applicationName = "Practico";

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping("/autor")
    public ResponseEntity<AutorDTO> createAutor(@Valid @RequestBody AutorDTO autorDTO) throws URISyntaxException {
        log.debug("REST request to save Autor : {}", autorDTO);
        if (autorDTO.getId() != null) {
            throw new BadRequestAlertException("A new autor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AutorDTO result = autorService.save(autorDTO);
        return ResponseEntity.created(new URI("/api/autor/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/autor")
    public ResponseEntity<AutorDTO> updateAutor(@Valid @RequestBody AutorDTO autorDTO) throws URISyntaxException {
        log.debug("REST request to update Autor : {}", autorDTO);
        if (autorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AutorDTO result =autorService.save(autorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, autorDTO.getId().toString()))
            .body(result);
    }
	
	
    @GetMapping("/autor")
    public ResponseEntity<List<AutorDTO>> getAllAutors(@RequestParam(value = "page", defaultValue = "0") int pageNumber,
		@RequestParam(value = "size", defaultValue = "10") int pageSize) {
        log.debug("REST request to get a page of Autors");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<AutorDTO> page =autorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	

    @GetMapping("/autor/{id}")
    public ResponseEntity<AutorDTO> getAutor(@PathVariable Long id) {
        log.debug("REST request to get Autor : {}", id);
        Optional<AutorDTO> autorDTO =autorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(autorDTO);
    }

    @DeleteMapping("/autor/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        log.debug("REST request to delete Autor : {}", id);
        autorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

