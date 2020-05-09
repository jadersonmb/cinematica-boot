package com.cinematica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.resources.AgendaResource;
import com.cinematica.services.AgendaServiceImpl;

/**
 * AgendaTest
 */
public class AgendaTest extends CinematicaApplicationTests {

    @Autowired
    private AgendaServiceImpl agendaService;
    @Autowired
    private AgendaResource agendaResource;
    
    private MockMvc mockMvc;

    @Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(agendaResource).build();
    }

    @Test
    public void testGETbuscarPorID() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/agendas/68"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    @Ignore
    public void buscarAgendaDaSemanaPorPaciente(){
        List<AgendaDTO> dto = agendaService.buscarAgendaDaSemanaPorPaciente(68);
        assertTrue(dto.size() > 0);
    }

    @Test
    public void buscarAgendaNãoExiste(){
        try {
            agendaService.buscarPorId(6893849489333L);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Agenda não existe");
        }
    }
}