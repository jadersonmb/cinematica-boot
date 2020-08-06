package com.cinematica;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.resources.PessoaResource;
import com.cinematica.services.pessoa.PessoaServiceImpl;

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

    @Ignore
    @Test
    public void testConsultarPacientePorID() throws Exception {
        PessoaDTO entidade = pessoaService.buscarPorId(80);
        assertNotNull(entidade.getId());
    }
}