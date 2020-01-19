package com.cinematica;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cinematica.model.Pessoa;
import com.cinematica.resources.PessoaResource;
import com.cinematica.service.PessoaServiceImpl;

/**
 * PessoaTest
 */
public class PessoaTest extends CinematicaApplicationTests {

    @Autowired
    private PessoaResource pessoaResource;
    @Autowired
    private PessoaServiceImpl pessoaService;

    private MockMvc mockMvc;

    @Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(pessoaResource).build();
    }
    
    @Test
	public void testGETlistaTodosPaciente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/pacientes")).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void testGETbuscarPorID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testConsultarPacientePorID() throws Exception {
        Pessoa entidade = pessoaService.buscarPorId(1);
        assertNotNull(entidade.getId());
    }

    @Test
    public void testListaTodosPaciente() {
        List<Pessoa> list = pessoaService.listarTodos();
        assertTrue(!list.isEmpty());
    }
}