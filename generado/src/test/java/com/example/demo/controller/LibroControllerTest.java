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
public class LibroControllerTest {

	private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroMapper libroMapper;

    @Autowired
    private LibroService libroService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLibroMockMvc;

    private Libro libro;

    public static Libro createEntity(EntityManager em) {
        Libro libro = new Libro();

        libro.setTitulo(DEFAULT_TITULO);
        return libro;
    }

    public static Libro createUpdatedEntity(EntityManager em) {
        Libro libro = new Libro();
        libro.setTitulo(UPDATED_TITULO);
        return libro;
    }

    @BeforeEach
    public void initTest() {
        libro = createEntity(em);
    }
	
    @Test
    @Transactional
    public void createLibro() throws Exception {
        int databaseSizeBeforeCreate = libroRepository.findAll().size();
        
        LibroDTO libroDTO = libroMapper.toDto(libro);
        restLibroMockMvc.perform(post("/api/libro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libroDTO)))
            .andExpect(status().isCreated());

        
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeCreate + 1);
        Libro testLibro = libroList.get(libroList.size() - 1);

		assertThat(testLibro.getTitulo()).isEqualTo(DEFAULT_TITULO);
    }

	@Test
    @Transactional
    public void updateLibro() throws Exception {
        
        libroRepository.saveAndFlush(libro);

        int databaseSizeBeforeUpdate = libroRepository.findAll().size();

        
        Libro updatedLibro = libroRepository.findById(libro.getId()).get();
        
        em.detach(updatedLibro);

        libro.setTitulo(UPDATED_TITULO);

        LibroDTO libroDTO = libroMapper.toDto(updatedLibro);

        restLibroMockMvc.perform(put("/api/libro")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libroDTO)))
            .andExpect(status().isOk());

        
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeUpdate);
        Libro testLibro = libroList.get(libroList.size() - 1);
		assertThat(testLibro.getTitulo()).isEqualTo(UPDATED_TITULO);
    }
	
    @Test
    @Transactional
    public void deleteLibro() throws Exception {
        
        libroRepository.saveAndFlush(libro);

        int databaseSizeBeforeDelete = libroRepository.findAll().size();

        
        restLibroMockMvc.perform(delete("/api/libro/{id}", libro.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        
        List<Libro> libroList = libroRepository.findAll();
        assertThat(libroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
