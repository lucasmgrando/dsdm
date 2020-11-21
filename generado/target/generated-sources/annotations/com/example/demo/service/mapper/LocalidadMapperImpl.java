package com.example.demo.service.mapper;

import com.example.demo.domain.Localidad;
import com.example.demo.domain.Provincia;
import com.example.demo.service.dto.LocalidadDTO;
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
public class LocalidadMapperImpl implements LocalidadMapper {

    @Autowired
    private ProvinciaMapper provinciaMapper;

    @Override
    public List<Localidad> toEntity(List<LocalidadDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Localidad> list = new ArrayList<Localidad>( dtoList.size() );
        for ( LocalidadDTO localidadDTO : dtoList ) {
            list.add( toEntity( localidadDTO ) );
        }

        return list;
    }

    @Override
    public List<LocalidadDTO> toDto(List<Localidad> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LocalidadDTO> list = new ArrayList<LocalidadDTO>( entityList.size() );
        for ( Localidad localidad : entityList ) {
            list.add( toDto( localidad ) );
        }

        return list;
    }

    @Override
    public LocalidadDTO toDto(Localidad localidad) {
        if ( localidad == null ) {
            return null;
        }

        LocalidadDTO localidadDTO = new LocalidadDTO();

        localidadDTO.setProvinciaId( localidadProvinciaId( localidad ) );
        localidadDTO.setId( localidad.getId() );
        localidadDTO.setDescripcion( localidad.getDescripcion() );

        return localidadDTO;
    }

    @Override
    public Localidad toEntity(LocalidadDTO localidadDTO) {
        if ( localidadDTO == null ) {
            return null;
        }

        Localidad localidad = new Localidad();

        localidad.setProvincia( provinciaMapper.fromId( localidadDTO.getprovinciaId() ) );
        localidad.setId( localidadDTO.getId() );
        localidad.setDescripcion( localidadDTO.getDescripcion() );

        return localidad;
    }

    private Long localidadProvinciaId(Localidad localidad) {
        if ( localidad == null ) {
            return null;
        }
        Provincia provincia = localidad.getProvincia();
        if ( provincia == null ) {
            return null;
        }
        Long id = provincia.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
