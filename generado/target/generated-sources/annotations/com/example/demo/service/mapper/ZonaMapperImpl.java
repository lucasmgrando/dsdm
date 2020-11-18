package com.example.demo.service.mapper;

import com.example.demo.domain.Zona;
import com.example.demo.service.dto.ZonaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-17T21:23:39-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class ZonaMapperImpl implements ZonaMapper {

    @Override
    public List<Zona> toEntity(List<ZonaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Zona> list = new ArrayList<Zona>( dtoList.size() );
        for ( ZonaDTO zonaDTO : dtoList ) {
            list.add( toEntity( zonaDTO ) );
        }

        return list;
    }

    @Override
    public List<ZonaDTO> toDto(List<Zona> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ZonaDTO> list = new ArrayList<ZonaDTO>( entityList.size() );
        for ( Zona zona : entityList ) {
            list.add( toDto( zona ) );
        }

        return list;
    }

    @Override
    public ZonaDTO toDto(Zona zona) {
        if ( zona == null ) {
            return null;
        }

        ZonaDTO zonaDTO = new ZonaDTO();

        zonaDTO.setId( zona.getId() );
        zonaDTO.setDescripcion( zona.getDescripcion() );

        return zonaDTO;
    }

    @Override
    public Zona toEntity(ZonaDTO zonaDTO) {
        if ( zonaDTO == null ) {
            return null;
        }

        Zona zona = new Zona();

        zona.setId( zonaDTO.getId() );
        zona.setDescripcion( zonaDTO.getDescripcion() );

        return zona;
    }
}
