package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.DomicilioDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
LocalidadMapper.class,
})
public interface DomicilioMapper extends EntityMapper<DomicilioDTO, Domicilio> {
	
    // @Mapping(source = "localidad.id", target = "localidadId")
    DomicilioDTO toDto(Domicilio domicilio);

    // @Mapping(source = "localidadId", target = "localidad")
    Domicilio toEntity(DomicilioDTO domicilioDTO);

    default Domicilio fromId(Long id) {
        if (id == null) {
            return null;
        }
        Domicilio domicilio = new Domicilio();
        domicilio.setId(id);
        return domicilio;
    }
}
