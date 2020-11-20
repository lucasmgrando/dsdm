package com.example.demo.service.mapper;

import com.example.demo.domain.Pais;
import com.example.demo.service.dto.PaisDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-20T16:54:30-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class PaisMapperImpl implements PaisMapper {

    @Override
    public List<Pais> toEntity(List<PaisDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Pais> list = new ArrayList<Pais>( dtoList.size() );
        for ( PaisDTO paisDTO : dtoList ) {
            list.add( toEntity( paisDTO ) );
        }

        return list;
    }

    @Override
    public List<PaisDTO> toDto(List<Pais> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PaisDTO> list = new ArrayList<PaisDTO>( entityList.size() );
        for ( Pais pais : entityList ) {
            list.add( toDto( pais ) );
        }

        return list;
    }

    @Override
    public PaisDTO toDto(Pais pais) {
        if ( pais == null ) {
            return null;
        }

        PaisDTO paisDTO = new PaisDTO();

        paisDTO.setId( pais.getId() );
        paisDTO.setDescripcion( pais.getDescripcion() );

        return paisDTO;
    }

    @Override
    public Pais toEntity(PaisDTO paisDTO) {
        if ( paisDTO == null ) {
            return null;
        }

        Pais pais = new Pais();

        pais.setId( paisDTO.getId() );
        pais.setDescripcion( paisDTO.getDescripcion() );

        return pais;
    }
}
