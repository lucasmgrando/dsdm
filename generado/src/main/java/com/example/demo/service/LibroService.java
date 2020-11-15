package com.example.demo.service;

import com.example.demo.domain.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.dto.LibroDTO;
import com.example.demo.service.mapper.LibroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LibroService {

    private final Logger log = LoggerFactory.getLogger(LibroService.class);

    private final LibroRepository libroRepository;

    private final LibroMapper libroMapper;

    public LibroService(LibroRepository libroRepository, LibroMapper libroMapper) {
        this.libroRepository = libroRepository;
        this.libroMapper = libroMapper;
    }

    public LibroDTO save(LibroDTO libroDTO) {
        log.debug("Request to save Libro : {}", libroDTO);
        Libro libro = libroMapper.toEntity(libroDTO);
        libro = libroRepository.save(libro);
        return libroMapper.toDto(libro);
    }

    @Transactional(readOnly = true)
    public Page<LibroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Libros");
        return libroRepository.findAll(pageable)
            .map(libroMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<LibroDTO> findOne(Long id) {
        log.debug("Request to get Libro : {}", id);
        return libroRepository.findById(id)
            .map(libroMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Libro : {}", id);
        libroRepository.deleteById(id);
    }
}
