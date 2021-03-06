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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cinematica.dto.ProfissaoDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.ProfissaoException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.services.profissao.ProfissaoService;

/**
 * 
 * @author Jaderson Morais
 *
 */
@RestController
@RequestMapping(value = "/profissoes")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfissaoResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProfissaoService profissaoService;
	@Autowired
	private MessageSource messageSource;

	public ResponseEntity<?> listarTodos() {
		List<ProfissaoDTO> listaProfissaoDTO = profissaoService.listarTodos();
		return ResponseEntity.ok().body(listaProfissaoDTO);
	}
	
	@GetMapping
	public ResponseEntity<?> listarTodosPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linePage", defaultValue="10") Integer linePage,
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="searchTerm", defaultValue= "") String searchTerm) {
		Page<ProfissaoDTO> listaProfissoesDTO = VerificadorUtil.naoEstaVazio(searchTerm)
				? profissaoService.search(searchTerm, page, linePage, orderBy, direction)
				: profissaoService.listarTodosPages(page, linePage, orderBy, direction);
		return ResponseEntity.ok().body(listaProfissoesDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		ProfissaoDTO entidade = profissaoService.buscarPorId(id);
		return ResponseEntity.ok().body(entidade);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody ProfissaoDTO entidadeDTO) {
		ProfissaoDTO profissaoDTO = profissaoService.salvar(entidadeDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(profissaoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(profissaoDTO);

	}
	
	@RequestMapping(value = "/deleteList/{ids}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteList(@PathVariable List<Long> ids) {
		profissaoService.deleteList(ids);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		ProfissaoDTO entidade = profissaoService.buscarPorId(id);
		profissaoService.delete(entidade);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody ProfissaoDTO entidadeDTO) {
		ProfissaoDTO entidadeSalvaDTO = profissaoService.buscarPorId(Long.valueOf(id));
		if(VerificadorUtil.naoEstaNulo(entidadeSalvaDTO.getId())) {
			entidadeSalvaDTO.setDescricao(entidadeDTO.getDescricao());
			profissaoService.salvar(entidadeSalvaDTO);
		}
		return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler({ ProfissaoException.class })
	public ResponseEntity<Object> ProfissaoException(ProfissaoException ex) {
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
