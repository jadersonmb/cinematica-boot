package com.cinematica.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.repository.especialidade.EspecialidadeFilterDTO;
import com.cinematica.services.especialidade.EspecialidadeService;

/**
 * 
 * @author Jaderson Morais
 *
 */
@RestController
@RequestMapping(value = "/especialidades")
@CrossOrigin(origins = "http://localhost:3000")
public class EspecialidadeResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EspecialidadeService especialidadeService;
	private MessageSource messageSource;

	@GetMapping
	public ResponseEntity<?> listarTodos(Pageable pageable, EspecialidadeFilterDTO filter) {
		Page<EspecialidadeDTO> listaEspecialidadeDTO = especialidadeService.listarTodos(pageable, filter);
		return ResponseEntity.ok().body(listaEspecialidadeDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		EspecialidadeDTO entidade = especialidadeService.buscarPorId(id);
		return ResponseEntity.ok().body(entidade);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid EspecialidadeDTO entidadeDTO) {
		EspecialidadeDTO especialidadeDTO = especialidadeService.salvar(entidadeDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(especialidadeDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(especialidadeDTO);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		EspecialidadeDTO entidade = especialidadeService.buscarPorId(id);
		especialidadeService.delete(entidade);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/deleteList/{ids}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteList(@PathVariable List<Integer> ids) {
		especialidadeService.deleteList(ids);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody EspecialidadeDTO entidadeDTO) {
		EspecialidadeDTO entidadeSalvaDTO = especialidadeService.buscarPorId(Integer.valueOf(id));
		if(VerificadorUtil.naoEstaNulo(entidadeSalvaDTO.getId())) {
			entidadeSalvaDTO.setDescricao(entidadeDTO.getDescricao());
			especialidadeService.salvar(entidadeSalvaDTO);
		}
		return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler({ EspecialidadeException.class })
	public ResponseEntity<Object> EspecialidadeException(EspecialidadeException ex) {
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
