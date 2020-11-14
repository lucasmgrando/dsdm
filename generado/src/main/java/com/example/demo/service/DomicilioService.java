package com.example.demo.service;

import com.example.demo.domain.Domicilio;
import com.example.demo.repository.DomicilioRepository;
import com.example.demo.service.dto.DomicilioDTO;
import com.example.demo.service.mapper.DomicilioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DomicilioService {

    private final Logger log = LoggerFactory.getLogger(DomicilioService.class);

    private final DomicilioRepository domicilioRepository;

    private final DomicilioMapper domicilioMapper;

    public DomicilioService(DomicilioRepository domicilioRepository, DomicilioMapper domicilioMapper) {
        this.domicilioRepository = domicilioRepository;
        this.domicilioMapper = domicilioMapper;
    }

    public DomicilioDTO save(DomicilioDTO domicilioDTO) {
        log.debug("Request to save Domicilio : {}", domicilioDTO);
        Domicilio domicilio = domicilioMapper.toEntity(domicilioDTO);
        domicilio = domicilioRepository.save(domicilio);
        return domicilioMapper.toDto(domicilio);
    }

    @Transactional(readOnly = true)
    public Page<DomicilioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Domicilios");
        return domicilioRepository.findAll(pageable)
            .map(domicilioMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<DomicilioDTO> findOne(Long id) {
        log.debug("Request to get Domicilio : {}", id);
        return domicilioRepository.findById(id)
            .map(domicilioMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Domicilio : {}", id);
        domicilioRepository.deleteById(id);
    }
}
