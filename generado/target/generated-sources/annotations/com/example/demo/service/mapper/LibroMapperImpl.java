package com.example.demo.service.mapper;

import com.example.demo.domain.Libro;
import com.example.demo.service.dto.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-20T16:54:30-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class LibroMapperImpl implements LibroMapper {

    @Override
    public List<Libro> toEntity(List<LibroDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Libro> list = new ArrayList<Libro>( dtoList.size() );
        for ( LibroDTO libroDTO : dtoList ) {
            list.add( toEntity( libroDTO ) );
        }

        return list;
    }

    @Override
    public List<LibroDTO> toDto(List<Libro> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LibroDTO> list = new ArrayList<LibroDTO>( entityList.size() );
        for ( Libro libro : entityList ) {
            list.add( toDto( libro ) );
        }

        return list;
    }

    @Override
    public LibroDTO toDto(Libro libro) {
        if ( libro == null ) {
            return null;
        }

        LibroDTO libroDTO = new LibroDTO();

        libroDTO.setId( libro.getId() );
        libroDTO.setTitulo( libro.getTitulo() );

        return libroDTO;
    }

    @Override
    public Libro toEntity(LibroDTO libroDTO) {
        if ( libroDTO == null ) {
            return null;
        }

        Libro libro = new Libro();

        libro.setId( libroDTO.getId() );
        libro.setTitulo( libroDTO.getTitulo() );

        return libro;
    }
}
