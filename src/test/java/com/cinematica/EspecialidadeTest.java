package com.cinematica;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.resources.EspecialidadeResource;
import com.cinematica.services.especialidade.EspecialidadeServiceImpl;

public class EspecialidadeTest extends CinematicaApplicationTests {

    @Autowired
    private EspecialidadeResource especialidadeResource; 
    @Autowired
    private EspecialidadeServiceImpl especialidadeService;

    private MockMvc mockMvc;

    @Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(especialidadeResource).build();
    }
    
    @Test
	public void testGETlistaTodosPaciente() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/especialidades")).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void testGETbuscarPorID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/especialidades/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEspecialidadeID() {
    	EspecialidadeDTO dto = especialidadeService.buscarPorId(1);
    	assertNotNull(dto.getId());
    	assertTrue(dto.getId() == 1);
    }

}
