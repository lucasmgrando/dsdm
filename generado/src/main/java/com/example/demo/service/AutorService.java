package com.example.demo.service;

import com.example.demo.domain.Autor;
import com.example.demo.repository.AutorRepository;
import com.example.demo.service.dto.AutorDTO;
import com.example.demo.service.mapper.AutorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AutorService {

    private final Logger log = LoggerFactory.getLogger(AutorService.class);

    private final AutorRepository autorRepository;

    private final AutorMapper autorMapper;

    public AutorService(AutorRepository autorRepository, AutorMapper autorMapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }

    public AutorDTO save(AutorDTO autorDTO) {
        log.debug("Request to save Autor : {}", autorDTO);
        Autor autor = autorMapper.toEntity(autorDTO);
        autor = autorRepository.save(autor);
        return autorMapper.toDto(autor);
    }

    @Transactional(readOnly = true)
    public Page<AutorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Autors");
        return autorRepository.findAll(pageable)
            .map(autorMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<AutorDTO> findOne(Long id) {
        log.debug("Request to get Autor : {}", id);
        return autorRepository.findById(id)
            .map(autorMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Autor : {}", id);
        autorRepository.deleteById(id);
    }
}
