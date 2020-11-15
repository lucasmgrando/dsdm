package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.AutorDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
DomicilioMapper.class,
LibroMapper.class,
})
public interface AutorMapper extends EntityMapper<AutorDTO, Autor> {
	
    AutorDTO toDto(Autor autor);

    Autor toEntity(AutorDTO autorDTO);

    default Autor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Autor autor = new Autor();
        autor.setId(id);
        return autor;
    }
}
