package com.example.demo.service.mapper;


import com.example.demo.domain.*;
import com.example.demo.service.dto.ProvinciaDTO;

import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = {
LocalidadMapper.class,
})
public interface ProvinciaMapper extends EntityMapper<ProvinciaDTO, Provincia> {
	
    ProvinciaDTO toDto(Provincia provincia);

    @Mapping(target = "localidads", ignore = true)
    @Mapping(target = "removeLocalidad", ignore = true)
    Provincia toEntity(ProvinciaDTO provinciaDTO);

    default Provincia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provincia provincia = new Provincia();
        provincia.setId(id);
        return provincia;
    }
}
