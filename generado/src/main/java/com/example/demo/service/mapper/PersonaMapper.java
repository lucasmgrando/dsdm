package com.example.demo.service.mapper;


import com.example.demo.entity.*;
import com.example.demo.service.dto.PersonaDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
DomicilioMapper.class,
LibroMapper.class,
})
public interface PersonaMapper extends EntityMapper<PersonaDTO, Persona> {
	
    @Mapping(source = "domicilio.id", target = "domicilioId")
    @Mapping(source = "libro.id", target = "libroId")
    PersonaDTO toDto(Persona persona);

    @Mapping(source = "domicilioId", target = "domicilio")
    @Mapping(source = "libroId", target = "libro")
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
