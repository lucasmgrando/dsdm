package com.example.demo.service.mapper;

import com.example.demo.domain.Autor;
import com.example.demo.service.dto.AutorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-14T20:00:33-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Autowired
    private DomicilioMapper domicilioMapper;

    @Override
    public List<Autor> toEntity(List<AutorDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Autor> list = new ArrayList<Autor>( dtoList.size() );
        for ( AutorDTO autorDTO : dtoList ) {
            list.add( toEntity( autorDTO ) );
        }

        return list;
    }

    @Override
    public List<AutorDTO> toDto(List<Autor> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AutorDTO> list = new ArrayList<AutorDTO>( entityList.size() );
        for ( Autor autor : entityList ) {
            list.add( toDto( autor ) );
        }

        return list;
    }

    @Override
    public AutorDTO toDto(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setId( autor.getId() );
        autorDTO.setNombre( autor.getNombre() );
        autorDTO.setApellido( autor.getApellido() );
        autorDTO.setDomicilio( domicilioMapper.toDto( autor.getDomicilio() ) );

        return autorDTO;
    }

    @Override
    public Autor toEntity(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setId( autorDTO.getId() );
        autor.setNombre( autorDTO.getNombre() );
        autor.setApellido( autorDTO.getApellido() );
        autor.setDomicilio( domicilioMapper.toEntity( autorDTO.getDomicilio() ) );

        return autor;
    }
}
