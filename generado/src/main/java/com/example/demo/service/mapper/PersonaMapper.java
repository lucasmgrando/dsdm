package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.PersonaDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
DomicilioMapper.class,
})
public interface PersonaMapper extends EntityMapper<PersonaDTO, Persona> {
	
    PersonaDTO toDto(Persona persona);

    Persona toEntity(PersonaDTO personaDTO);

    default Persona fromId(Long id) {
        if (id == null) {
            return null;
        }
        Persona persona = new Persona();
        persona.setId(id);
        return persona;
    }
}
