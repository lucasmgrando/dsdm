package com.example.demo.service.mapper;


import com.example.demo.entity.*;
import com.example.demo.service.dto.LibroDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
AutorMapper.class,
})
public interface LibroMapper extends EntityMapper<LibroDTO, Libro> {
	
    @Mapping(source = "autor.id", target = "autorId")
    LibroDTO toDto(Libro libro);

    @Mapping(source = "autorId", target = "autor")
    Libro toEntity(LibroDTO libroDTO);

    default Libro fromId(Long id) {
        if (id == null) {
            return null;
        }
        Libro libro = new Libro();
        libro.setId(id);
        return libro;
    }
}
