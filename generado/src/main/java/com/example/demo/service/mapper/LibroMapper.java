package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.LibroDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
})
public interface LibroMapper extends EntityMapper<LibroDTO, Libro> {
	
    LibroDTO toDto(Libro libro);

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
