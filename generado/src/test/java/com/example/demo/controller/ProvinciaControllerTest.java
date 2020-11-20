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
public class ProvinciaControllerTest {

	private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ProvinciaMapper provinciaMapper;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProvinciaMockMvc;

    private Provincia provincia;

    public static Provincia createEntity(EntityManager em) {
        Provincia provincia = new Provincia();

        provincia.setDescripcion(DEFAULT_DESCRIPCION);
        return provincia;
    }

    public static Provincia createUpdatedEntity(EntityManager em) {
        Provincia provincia = new Provincia();
        provincia.setDescripcion(UPDATED_DESCRIPCION);
        return provincia;
    }

    @BeforeEach
    public void initTest() {
        provincia = createEntity(em);
    }
	
    @Test
    @Transactional
    public void createProvincia() throws Exception {
        int databaseSizeBeforeCreate = provinciaRepository.findAll().size();
        
        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(provincia);
        restProvinciaMockMvc.perform(post("/api/provincia")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isCreated());

        
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeCreate + 1);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);

		assertThat(testProvincia.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

	@Test
    @Transactional
    public void updateProvincia() throws Exception {
        
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeUpdate = provinciaRepository.findAll().size();

        
        Provincia updatedProvincia = provinciaRepository.findById(provincia.getId()).get();
        
        em.detach(updatedProvincia);

        provincia.setDescripcion(UPDATED_DESCRIPCION);

        ProvinciaDTO provinciaDTO = provinciaMapper.toDto(updatedProvincia);

        restProvinciaMockMvc.perform(put("/api/provincia")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(provinciaDTO)))
            .andExpect(status().isOk());

        
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeUpdate);
        Provincia testProvincia = provinciaList.get(provinciaList.size() - 1);
		assertThat(testProvincia.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }
	
    @Test
    @Transactional
    public void deleteProvincia() throws Exception {
        
        provinciaRepository.saveAndFlush(provincia);

        int databaseSizeBeforeDelete = provinciaRepository.findAll().size();

        
        restProvinciaMockMvc.perform(delete("/api/provincia/{id}", provincia.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        
        List<Provincia> provinciaList = provinciaRepository.findAll();
        assertThat(provinciaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
