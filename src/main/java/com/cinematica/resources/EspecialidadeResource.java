package com.cinematica.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.UsuarioException;
import com.cinematica.interfaces.services.EspecialidadeService;
import com.cinematica.model.Especialidade;

/**
 * 
 * @author Jaderson Morais
 *
 */
@RestController
@RequestMapping(value = "/especialidades")
@CrossOrigin(origins = "http://localhost:4200")
public class EspecialidadeResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EspecialidadeService especialidadeService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public ResponseEntity<?> listarTodos() {
		List<EspecialidadeDTO> listaEspecialidadeDTO = especialidadeService.listarTodos();
		return ResponseEntity.ok().body(listaEspecialidadeDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		EspecialidadeDTO entidade = especialidadeService.buscarPorId(id);
		return ResponseEntity.ok().body(entidade);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Especialidade entidade) {
		EspecialidadeDTO especialidadeDTO = especialidadeService.salvar(entidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(especialidadeDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(especialidadeDTO);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		EspecialidadeDTO entidade = especialidadeService.buscarPorId(id);
		especialidadeService.delete(entidade);
		return ResponseEntity.ok().build();
	}

	@ExceptionHandler({ UsuarioException.class })
	public ResponseEntity<Object> EspecialidadeException(UsuarioException ex) {
		String mensagemUsuario = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}
