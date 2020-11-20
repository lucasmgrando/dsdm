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
public class PaisControllerTest {

	private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private PaisMapper paisMapper;

    @Autowired
    private PaisService paisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaisMockMvc;

    private Pais pais;

    public static Pais createEntity(EntityManager em) {
        Pais pais = new Pais();

        pais.setDescripcion(DEFAULT_DESCRIPCION);
        return pais;
    }

    public static Pais createUpdatedEntity(EntityManager em) {
        Pais pais = new Pais();
        pais.setDescripcion(UPDATED_DESCRIPCION);
        return pais;
    }

    @BeforeEach
    public void initTest() {
        pais = createEntity(em);
    }
	
    @Test
    @Transactional
    public void createPais() throws Exception {
        int databaseSizeBeforeCreate = paisRepository.findAll().size();
        
        PaisDTO paisDTO = paisMapper.toDto(pais);
        restPaisMockMvc.perform(post("/api/pais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paisDTO)))
            .andExpect(status().isCreated());

        
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeCreate + 1);
        Pais testPais = paisList.get(paisList.size() - 1);

		assertThat(testPais.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

	@Test
    @Transactional
    public void updatePais() throws Exception {
        
        paisRepository.saveAndFlush(pais);

        int databaseSizeBeforeUpdate = paisRepository.findAll().size();

        
        Pais updatedPais = paisRepository.findById(pais.getId()).get();
        
        em.detach(updatedPais);

        pais.setDescripcion(UPDATED_DESCRIPCION);

        PaisDTO paisDTO = paisMapper.toDto(updatedPais);

        restPaisMockMvc.perform(put("/api/pais")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paisDTO)))
            .andExpect(status().isOk());

        
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeUpdate);
        Pais testPais = paisList.get(paisList.size() - 1);
		assertThat(testPais.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }
	
    @Test
    @Transactional
    public void deletePais() throws Exception {
        
        paisRepository.saveAndFlush(pais);

        int databaseSizeBeforeDelete = paisRepository.findAll().size();

        
        restPaisMockMvc.perform(delete("/api/pais/{id}", pais.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        
        List<Pais> paisList = paisRepository.findAll();
        assertThat(paisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
