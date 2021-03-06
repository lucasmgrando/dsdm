package com.example.demo.service.mapper;

import com.example.demo.domain.Persona;
import com.example.demo.service.dto.PersonaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-21T20:12:27-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class PersonaMapperImpl implements PersonaMapper {

    @Autowired
    private DomicilioMapper domicilioMapper;

    @Override
    public List<Persona> toEntity(List<PersonaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Persona> list = new ArrayList<Persona>( dtoList.size() );
        for ( PersonaDTO personaDTO : dtoList ) {
            list.add( toEntity( personaDTO ) );
        }

        return list;
    }

    @Override
    public List<PersonaDTO> toDto(List<Persona> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PersonaDTO> list = new ArrayList<PersonaDTO>( entityList.size() );
        for ( Persona persona : entityList ) {
            list.add( toDto( persona ) );
        }

        return list;
    }

    @Override
    public PersonaDTO toDto(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        PersonaDTO personaDTO = new PersonaDTO();

        personaDTO.setId( persona.getId() );
        personaDTO.setNombre( persona.getNombre() );
        personaDTO.setDomicilio( domicilioMapper.toDto( persona.getDomicilio() ) );

        return personaDTO;
    }

    @Override
    public Persona toEntity(PersonaDTO personaDTO) {
        if ( personaDTO == null ) {
            return null;
        }

        Persona persona = new Persona();

        persona.setId( personaDTO.getId() );
        persona.setNombre( personaDTO.getNombre() );
        persona.setDomicilio( domicilioMapper.toEntity( personaDTO.getDomicilio() ) );

        return persona;
    }
}
