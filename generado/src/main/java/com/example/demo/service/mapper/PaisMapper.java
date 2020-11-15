package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.PaisDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
})
public interface PaisMapper extends EntityMapper<PaisDTO, Pais> {
	
    PaisDTO toDto(Pais pais);

    Pais toEntity(PaisDTO paisDTO);

    default Pais fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pais pais = new Pais();
        pais.setId(id);
        return pais;
    }
}
