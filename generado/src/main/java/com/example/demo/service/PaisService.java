package com.example.demo.service;

import com.example.demo.domain.Pais;
import com.example.demo.repository.PaisRepository;
import com.example.demo.service.dto.PaisDTO;
import com.example.demo.service.mapper.PaisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PaisService {

    private final Logger log = LoggerFactory.getLogger(PaisService.class);

    private final PaisRepository paisRepository;

    private final PaisMapper paisMapper;

    public PaisService(PaisRepository paisRepository, PaisMapper paisMapper) {
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
    }

    public PaisDTO save(PaisDTO paisDTO) {
        log.debug("Request to save Pais : {}", paisDTO);
        Pais pais = paisMapper.toEntity(paisDTO);
        pais = paisRepository.save(pais);
        return paisMapper.toDto(pais);
    }

    @Transactional(readOnly = true)
    public Page<PaisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Paiss");
        return paisRepository.findAll(pageable)
            .map(paisMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Optional<PaisDTO> findOne(Long id) {
        log.debug("Request to get Pais : {}", id);
        return paisRepository.findById(id)
            .map(paisMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete Pais : {}", id);
        paisRepository.deleteById(id);
    }
}
