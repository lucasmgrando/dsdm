package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.LocalidadDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
ProvinciaMapper.class,
})
public interface LocalidadMapper extends EntityMapper<LocalidadDTO, Localidad> {
	
    @Mapping(source = "provincia.id", target = "provinciaId")
    LocalidadDTO toDto(Localidad localidad);

    @Mapping(source = "provinciaId", target = "provincia")
    Localidad toEntity(LocalidadDTO localidadDTO);

    default Localidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Localidad localidad = new Localidad();
        localidad.setId(id);
        return localidad;
    }
}
