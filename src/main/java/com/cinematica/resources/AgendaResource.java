package com.cinematica.resources;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.AgendaException;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.model.Agenda;
import com.cinematica.services.agenda.AgendaService;
import com.cinematica.services.pessoa.PessoaService;

/**
 * AgendaResource
 */
@RestController
@RequestMapping(value = "/agendas")
@CrossOrigin(origins = "http://localhost:4200")
public class AgendaResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AgendaService agendaService;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
	private MessageSource messageSource;

    @GetMapping
	public ResponseEntity<?> listarTodos() {
		List<AgendaDTO> listaAgendaDTO = agendaService.listarTodos();
		return ResponseEntity.ok().body(listaAgendaDTO);
	}
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarAgendaPorIdPaciente(@PathVariable Integer id) {
        PessoaDTO entidadePessoa = pessoaService.buscarPorId(id);
        Long idPaciente = Long.valueOf(entidadePessoa.getId());
        Agenda entidade = agendaService.buscarAgendaPorIdPaciente(idPaciente);
        return ResponseEntity.ok().body(entidade);
    }

    @GetMapping(value = "/funcionario/{id}")
    public ResponseEntity<?> buscarAgendaPorIdFuncionario(@PathVariable Integer id) {
        List<Agenda> entidade = agendaService.buscarAgendaPorFuncionarioId(id);
        return ResponseEntity.ok().body(entidade);
    }

    @GetMapping(value = "/paciente")
    public ResponseEntity<List<AgendaDTO>> buscarAgendaDaSemanaPorPaciente(@RequestParam Integer id) {
        List<AgendaDTO> entidade = agendaService.buscarAgendaDaSemanaPorPaciente(id);
        return ResponseEntity.ok().body(entidade);
    }

    @ExceptionHandler({AgendaException.class})
	public ResponseEntity<Object> AgendaException(AgendaException ex) {
    	String mensagemUsuario = "";
		String[] split = ex.getMessage().split(";");
		for (String exMessage : split) {
			mensagemUsuario = mensagemUsuario + messageSource.getMessage(exMessage, null, LocaleContextHolder.getLocale()).concat(",");
		}
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario.replaceFirst("(,$)", ""), mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
    
}