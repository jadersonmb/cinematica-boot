package com.cinematica.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cinematica.dto.HorarioDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.HorarioException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.services.horario.HorarioService;

@RestController
@RequestMapping(value = "/horarios")
@CrossOrigin(origins = "http://localhost:4200")
public class HorarioResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private HorarioService horarioService;
	@Autowired
	private MessageSource messageSource;

	public ResponseEntity<?> listarTodos() {
		List<HorarioDTO> listaEspecialidadeDTO = horarioService.listarTodos();
		return ResponseEntity.ok().body(listaEspecialidadeDTO);
	}
	
	@GetMapping
	public ResponseEntity<?> listarTodosPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linePage", defaultValue="10") Integer linePage,
			@RequestParam(value="orderBy", defaultValue="horarioInicio") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="searchTerm", defaultValue= "") String searchTerm) {
		Page<HorarioDTO> listaEspecialidadeDTO = VerificadorUtil.naoEstaVazio(searchTerm)
				? horarioService.search(searchTerm, page, linePage, orderBy, direction)
				: horarioService.listarTodosPages(page, linePage, orderBy, direction);
		return ResponseEntity.ok().body(listaEspecialidadeDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		HorarioDTO entidade = horarioService.buscarPorId(id);
		return ResponseEntity.ok().body(entidade);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody HorarioDTO entidadeDTO) {
		HorarioDTO horarioDTO = horarioService.salvar(entidadeDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(horarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(horarioDTO);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		HorarioDTO entidade = horarioService.buscarPorId(id);
		horarioService.delete(entidade);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody HorarioDTO entidadeDTO) {
		HorarioDTO entidadeSalvaDTO = horarioService.buscarPorId(id);
		if(VerificadorUtil.naoEstaNulo(entidadeSalvaDTO.getId())) {
			entidadeSalvaDTO.setHorarioInicio(entidadeDTO.getHorarioInicio());
			entidadeSalvaDTO.setHorarioFim(entidadeDTO.getHorarioFim());
			horarioService.salvar(entidadeSalvaDTO);
		}
		return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler({ com.cinematica.exception.HorarioException.class })
	public ResponseEntity<Object> HorarioException(HorarioException ex) {
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
