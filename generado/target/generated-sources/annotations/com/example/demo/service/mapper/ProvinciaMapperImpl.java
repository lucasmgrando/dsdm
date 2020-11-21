package com.example.demo.service.mapper;

import com.example.demo.domain.Pais;
import com.example.demo.domain.Provincia;
import com.example.demo.domain.Zona;
import com.example.demo.service.dto.ProvinciaDTO;
import com.example.demo.service.dto.ZonaDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-21T20:12:27-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (BellSoft)"
)
@Component
public class ProvinciaMapperImpl implements ProvinciaMapper {

    @Autowired
    private ZonaMapper zonaMapper;
    @Autowired
    private PaisMapper paisMapper;

    @Override
    public List<Provincia> toEntity(List<ProvinciaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Provincia> list = new ArrayList<Provincia>( dtoList.size() );
        for ( ProvinciaDTO provinciaDTO : dtoList ) {
            list.add( toEntity( provinciaDTO ) );
        }

        return list;
    }

    @Override
    public List<ProvinciaDTO> toDto(List<Provincia> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProvinciaDTO> list = new ArrayList<ProvinciaDTO>( entityList.size() );
        for ( Provincia provincia : entityList ) {
            list.add( toDto( provincia ) );
        }

        return list;
    }

    @Override
    public ProvinciaDTO toDto(Provincia provincia) {
        if ( provincia == null ) {
            return null;
        }

        ProvinciaDTO provinciaDTO = new ProvinciaDTO();

        provinciaDTO.setPaisId( provinciaPaisId( provincia ) );
        provinciaDTO.setId( provincia.getId() );
        provinciaDTO.setDescripcion( provincia.getDescripcion() );
        provinciaDTO.setZonas( zonaSetToZonaDTOSet( provincia.getZonas() ) );

        return provinciaDTO;
    }

    @Override
    public Provincia toEntity(ProvinciaDTO provinciaDTO) {
        if ( provinciaDTO == null ) {
            return null;
        }

        Provincia provincia = new Provincia();

        provincia.setPais( paisMapper.fromId( provinciaDTO.getpaisId() ) );
        provincia.setId( provinciaDTO.getId() );
        provincia.setDescripcion( provinciaDTO.getDescripcion() );
        provincia.setZonas( zonaDTOSetToZonaSet( provinciaDTO.getZonas() ) );

        return provincia;
    }

    private Long provinciaPaisId(Provincia provincia) {
        if ( provincia == null ) {
            return null;
        }
        Pais pais = provincia.getPais();
        if ( pais == null ) {
            return null;
        }
        Long id = pais.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<ZonaDTO> zonaSetToZonaDTOSet(Set<Zona> set) {
        if ( set == null ) {
            return null;
        }

        Set<ZonaDTO> set1 = new HashSet<ZonaDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Zona zona : set ) {
            set1.add( zonaMapper.toDto( zona ) );
        }

        return set1;
    }

    protected Set<Zona> zonaDTOSetToZonaSet(Set<ZonaDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Zona> set1 = new HashSet<Zona>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ZonaDTO zonaDTO : set ) {
            set1.add( zonaMapper.toEntity( zonaDTO ) );
        }

        return set1;
    }
}
