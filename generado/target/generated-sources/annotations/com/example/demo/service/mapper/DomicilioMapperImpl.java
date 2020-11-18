package com.example.demo.service.mapper;

import com.example.demo.domain.Domicilio;
import com.example.demo.domain.Localidad;
import com.example.demo.service.dto.DomicilioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-17T21:23:39-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class DomicilioMapperImpl implements DomicilioMapper {

    @Autowired
    private LocalidadMapper localidadMapper;

    @Override
    public List<Domicilio> toEntity(List<DomicilioDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Domicilio> list = new ArrayList<Domicilio>( dtoList.size() );
        for ( DomicilioDTO domicilioDTO : dtoList ) {
            list.add( toEntity( domicilioDTO ) );
        }

        return list;
    }

    @Override
    public List<DomicilioDTO> toDto(List<Domicilio> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DomicilioDTO> list = new ArrayList<DomicilioDTO>( entityList.size() );
        for ( Domicilio domicilio : entityList ) {
            list.add( toDto( domicilio ) );
        }

        return list;
    }

    @Override
    public DomicilioDTO toDto(Domicilio domicilio) {
        if ( domicilio == null ) {
            return null;
        }

        DomicilioDTO domicilioDTO = new DomicilioDTO();

        domicilioDTO.setLocalidadId( domicilioLocalidadId( domicilio ) );
        domicilioDTO.setId( domicilio.getId() );
        domicilioDTO.setDescripcion( domicilio.getDescripcion() );

        return domicilioDTO;
    }

    @Override
    public Domicilio toEntity(DomicilioDTO domicilioDTO) {
        if ( domicilioDTO == null ) {
            return null;
        }

        Domicilio domicilio = new Domicilio();

        domicilio.setLocalidad( localidadMapper.fromId( domicilioDTO.getlocalidadId() ) );
        domicilio.setId( domicilioDTO.getId() );
        domicilio.setDescripcion( domicilioDTO.getDescripcion() );

        return domicilio;
    }

    private Long domicilioLocalidadId(Domicilio domicilio) {
        if ( domicilio == null ) {
            return null;
        }
        Localidad localidad = domicilio.getLocalidad();
        if ( localidad == null ) {
            return null;
        }
        Long id = localidad.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
