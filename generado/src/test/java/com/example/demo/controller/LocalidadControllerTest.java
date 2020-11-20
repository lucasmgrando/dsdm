package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.service.dto.*;
import com.example.demo.service.mapper.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class LocalidadControllerTest {

	private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private LocalidadMapper localidadMapper;

    @Autowired
    private LocalidadService localidadService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocalidadMockMvc;

    private Localidad localidad;

    public static Localidad createEntity(EntityManager em) {
        Localidad localidad = new Localidad();

        localidad.setDescripcion(DEFAULT_DESCRIPCION);
        return localidad;
    }

    public static Localidad createUpdatedEntity(EntityManager em) {
        Localidad localidad = new Localidad();
        localidad.setDescripcion(UPDATED_DESCRIPCION);
        return localidad;
    }

    @BeforeEach
    public void initTest() {
        localidad = createEntity(em);
    }
	
    @Test
    @Transactional
    public void createLocalidad() throws Exception {
        int databaseSizeBeforeCreate = localidadRepository.findAll().size();
        
        LocalidadDTO localidadDTO = localidadMapper.toDto(localidad);
        restLocalidadMockMvc.perform(post("/api/localidad")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(localidadDTO)))
            .andExpect(status().isCreated());

        
        List<Localidad> localidadList = localidadRepository.findAll();
        assertThat(localidadList).hasSize(databaseSizeBeforeCreate + 1);
        Localidad testLocalidad = localidadList.get(localidadList.size() - 1);

		assertThat(testLocalidad.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

	@Test
    @Transactional
    public void updateLocalidad() throws Exception {
        
        localidadRepository.saveAndFlush(localidad);

        int databaseSizeBeforeUpdate = localidadRepository.findAll().size();

        
        Localidad updatedLocalidad = localidadRepository.findById(localidad.getId()).get();
        
        em.detach(updatedLocalidad);

        localidad.setDescripcion(UPDATED_DESCRIPCION);

        LocalidadDTO localidadDTO = localidadMapper.toDto(updatedLocalidad);

        restLocalidadMockMvc.perform(put("/api/localidad")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(localidadDTO)))
            .andExpect(status().isOk());

        
        List<Localidad> localidadList = localidadRepository.findAll();
        assertThat(localidadList).hasSize(databaseSizeBeforeUpdate);
        Localidad testLocalidad = localidadList.get(localidadList.size() - 1);
		assertThat(testLocalidad.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }
	
    @Test
    @Transactional
    public void deleteLocalidad() throws Exception {
        
        localidadRepository.saveAndFlush(localidad);

        int databaseSizeBeforeDelete = localidadRepository.findAll().size();

        
        restLocalidadMockMvc.perform(delete("/api/localidad/{id}", localidad.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        
        List<Localidad> localidadList = localidadRepository.findAll();
        assertThat(localidadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
