package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.ProvinciaDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
ZonaMapper.class,
PaisMapper.class,
})
public interface ProvinciaMapper extends EntityMapper<ProvinciaDTO, Provincia> {
	
    @Mapping(source = "pais.id", target = "paisId")
    ProvinciaDTO toDto(Provincia provincia);

    @Mapping(source = "paisId", target = "pais")
    Provincia toEntity(ProvinciaDTO provinciaDTO);

    default Provincia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provincia provincia = new Provincia();
        provincia.setId(id);
        return provincia;
    }
}
