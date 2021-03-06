package com.cinematica.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.cinematica.dto.FluxoCaixaDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.FluxoCaixaException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.repository.fluxoCaixa.FluxoCaixaFilterDTO;
import com.cinematica.services.empresa.EmpresaService;
import com.cinematica.services.fluxoCaixa.FluxoCaixaService;
import com.cinematica.services.usuario.UsuarioService;

/**
 * 
 * @author Jaderson Morais
 *
 */
@RestController
@RequestMapping(value = "/fluxoCaixa")
@CrossOrigin(origins = "http://localhost:4200")
public class FluxoCaixaResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private FluxoCaixaService fluxoCaixaService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public ResponseEntity<?> listarTodosPage(Pageable pageable, FluxoCaixaFilterDTO filter) {
		Page<FluxoCaixaDTO> listaFormaPagamentosDTO = fluxoCaixaService.listarTodosPages(pageable, filter);
		return ResponseEntity.ok().body(listaFormaPagamentosDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		FluxoCaixaDTO entidade = fluxoCaixaService.buscarPorId(id);
		return ResponseEntity.ok().body(entidade);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody FluxoCaixaDTO entidadeDTO) {
		//TODO: 
		entidadeDTO.setEmpresa(empresaService.buscarPorId(1));
		entidadeDTO.setUsuario(usuarioService.consultarPorId(1));
		
		FluxoCaixaDTO fluxoCaixaDTO = fluxoCaixaService.salvar(entidadeDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fluxoCaixaDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(fluxoCaixaDTO);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		FluxoCaixaDTO entidade = fluxoCaixaService.buscarPorId(id);
		fluxoCaixaService.delete(entidade);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/deleteList/{ids}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteList(@PathVariable List<Long> ids) {
		fluxoCaixaService.deleteList(ids);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FluxoCaixaDTO entidadeDTO) {
		FluxoCaixaDTO entidadeSalvaDTO = fluxoCaixaService.buscarPorId(id);
		if(VerificadorUtil.naoEstaNulo(entidadeSalvaDTO.getId())) {
			BeanUtils.copyProperties(entidadeDTO, entidadeSalvaDTO, "id");
			fluxoCaixaService.salvar(entidadeSalvaDTO);
		}
		return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler({ FluxoCaixaException.class })
	public ResponseEntity<Object> FluxoCaixaException(FluxoCaixaException ex) {
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
