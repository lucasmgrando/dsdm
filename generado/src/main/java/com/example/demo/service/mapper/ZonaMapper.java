package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.ZonaDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
})
public interface ZonaMapper extends EntityMapper<ZonaDTO, Zona> {
	
    ZonaDTO toDto(Zona zona);

    Zona toEntity(ZonaDTO zonaDTO);

    default Zona fromId(Long id) {
        if (id == null) {
            return null;
        }
        Zona zona = new Zona();
        zona.setId(id);
        return zona;
    }
}
