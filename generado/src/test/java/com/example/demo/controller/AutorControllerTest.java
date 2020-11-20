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
public class AutorControllerTest {

	private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorMapper autorMapper;

    @Autowired
    private AutorService autorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutorMockMvc;

    private Autor autor;

    public static Autor createEntity(EntityManager em) {
        Autor autor = new Autor();

        autor.setNombre(DEFAULT_NOMBRE);
        return autor;
    }

    public static Autor createUpdatedEntity(EntityManager em) {
        Autor autor = new Autor();
        autor.setNombre(UPDATED_NOMBRE);
        return autor;
    }

    @BeforeEach
    public void initTest() {
        autor = createEntity(em);
    }
	
    @Test
    @Transactional
    public void createAutor() throws Exception {
        int databaseSizeBeforeCreate = autorRepository.findAll().size();
        
        AutorDTO autorDTO = autorMapper.toDto(autor);
        restAutorMockMvc.perform(post("/api/autor")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autorDTO)))
            .andExpect(status().isCreated());

        
        List<Autor> autorList = autorRepository.findAll();
        assertThat(autorList).hasSize(databaseSizeBeforeCreate + 1);
        Autor testAutor = autorList.get(autorList.size() - 1);

		assertThat(testAutor.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

	@Test
    @Transactional
    public void updateAutor() throws Exception {
        
        autorRepository.saveAndFlush(autor);

        int databaseSizeBeforeUpdate = autorRepository.findAll().size();

        
        Autor updatedAutor = autorRepository.findById(autor.getId()).get();
        
        em.detach(updatedAutor);

        autor.setNombre(UPDATED_NOMBRE);

        AutorDTO autorDTO = autorMapper.toDto(updatedAutor);

        restAutorMockMvc.perform(put("/api/autor")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autorDTO)))
            .andExpect(status().isOk());

        
        List<Autor> autorList = autorRepository.findAll();
        assertThat(autorList).hasSize(databaseSizeBeforeUpdate);
        Autor testAutor = autorList.get(autorList.size() - 1);
		assertThat(testAutor.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }
	
    @Test
    @Transactional
    public void deleteAutor() throws Exception {
        
        autorRepository.saveAndFlush(autor);

        int databaseSizeBeforeDelete = autorRepository.findAll().size();

        
        restAutorMockMvc.perform(delete("/api/autor/{id}", autor.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        
        List<Autor> autorList = autorRepository.findAll();
        assertThat(autorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
