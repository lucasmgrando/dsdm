package com.example.demo.service.mapper;

import com.example.demo.domain.Autor;
import com.example.demo.domain.Libro;
import com.example.demo.service.dto.AutorDTO;
import com.example.demo.service.dto.LibroDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-14T15:16:50-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class LibroMapperImpl implements LibroMapper {

    @Autowired
    private AutorMapper autorMapper;

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
        libroDTO.setPaginas( libro.getPaginas() );
        libroDTO.setGenero( libro.getGenero() );
        libroDTO.setFecha( libro.getFecha() );
        libroDTO.setAutors( autorSetToAutorDTOSet( libro.getAutors() ) );

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
        libro.setPaginas( libroDTO.getPaginas() );
        libro.setGenero( libroDTO.getGenero() );
        libro.setFecha( libroDTO.getFecha() );

        return libro;
    }

    protected Set<AutorDTO> autorSetToAutorDTOSet(Set<Autor> set) {
        if ( set == null ) {
            return null;
        }

        Set<AutorDTO> set1 = new HashSet<AutorDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Autor autor : set ) {
            set1.add( autorMapper.toDto( autor ) );
        }

        return set1;
    }
}
