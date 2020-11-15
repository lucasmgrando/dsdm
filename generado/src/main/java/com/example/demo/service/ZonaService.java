package com.example.demo.service;

import com.example.demo.domain.Zona;
import com.example.demo.repository.ZonaRepository;
import com.example.demo.service.dto.ZonaDTO;
import com.example.demo.service.mapper.ZonaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ZonaService {

    private final Logger log = LoggerFactory.getLogger(ZonaService.class);

    private final ZonaRepository zonaRepository;

    private final ZonaMapper zonaMapper;

    public ZonaService(ZonaRepository zonaRepository, ZonaMapper zonaMapper) {
        this.zonaRepository = zonaRepository;
        this.zonaMapper = zonaMapper;
    }

    public ZonaDTO save(ZonaDTO zonaDTO) {
        log.debug("Request to save Zona : {}", zonaDTO);
        Zona zona = zonaMapper.toEntity(zonaDTO);
        zona = zonaRepository.save(zona);
        return zonaMapper.toDto(zona);
    }

    @Transactional(readOnly = true)
    public Page<ZonaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Zonas");
        return zonaRepository.findAll(pageable)
            .map(zonaMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<ZonaDTO> findOne(Long id) {
        log.debug("Request to get Zona : {}", id);
        return zonaRepository.findById(id)
            .map(zonaMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Zona : {}", id);
        zonaRepository.deleteById(id);
    }
}
