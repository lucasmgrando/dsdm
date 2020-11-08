package com.example.demo.service.mapper;


import com.example.demo.entity.*;
import com.example.demo.service.dto.LocalidadDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
})
public interface LocalidadMapper extends EntityMapper<LocalidadDTO, Localidad> {
	
    LocalidadDTO toDto(Localidad localidad);

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
