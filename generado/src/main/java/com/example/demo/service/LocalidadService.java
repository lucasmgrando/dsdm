package com.example.demo.service;

import com.example.demo.domain.Localidad;
import com.example.demo.repository.LocalidadRepository;
import com.example.demo.service.dto.LocalidadDTO;
import com.example.demo.service.mapper.LocalidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LocalidadService {

    private final Logger log = LoggerFactory.getLogger(LocalidadService.class);

    private final LocalidadRepository localidadRepository;

    private final LocalidadMapper localidadMapper;

    public LocalidadService(LocalidadRepository localidadRepository, LocalidadMapper localidadMapper) {
        this.localidadRepository = localidadRepository;
        this.localidadMapper = localidadMapper;
    }

    public LocalidadDTO save(LocalidadDTO localidadDTO) {
        log.debug("Request to save Localidad : {}", localidadDTO);
        Localidad localidad = localidadMapper.toEntity(localidadDTO);
        localidad = localidadRepository.save(localidad);
        return localidadMapper.toDto(localidad);
    }

    @Transactional(readOnly = true)
    public Page<LocalidadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Localidads");
        return localidadRepository.findAll(pageable)
            .map(localidadMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<LocalidadDTO> findOne(Long id) {
        log.debug("Request to get Localidad : {}", id);
        return localidadRepository.findById(id)
            .map(localidadMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Localidad : {}", id);
        localidadRepository.deleteById(id);
    }
}
