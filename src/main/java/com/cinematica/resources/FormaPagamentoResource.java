package com.cinematica.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cinematica.dto.FormaPagamentoDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.FormaPagamentoException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.services.formaPagamento.FormaPagamentoService;

@RestController
@RequestMapping(value = "/formaPagamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class FormaPagamentoResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public ResponseEntity<?> listarTodos() {
		List<FormaPagamentoDTO> listaFormaPagamentosDTO = formaPagamentoService.listarTodos();
		return ResponseEntity.ok().body(listaFormaPagamentosDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		FormaPagamentoDTO entidade = formaPagamentoService.buscarPorId(id);
		return ResponseEntity.ok().body(entidade);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody FormaPagamentoDTO entidadeDTO) {
		FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.salvar(entidadeDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(formaPagamentoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(formaPagamentoDTO);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		FormaPagamentoDTO entidade = formaPagamentoService.buscarPorId(id);
		formaPagamentoService.delete(entidade);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FormaPagamentoDTO entidadeDTO) {
		FormaPagamentoDTO entidadeSalvaDTO = formaPagamentoService.buscarPorId(id);
		if(VerificadorUtil.naoEstaNulo(entidadeSalvaDTO.getId())) {
			BeanUtils.copyProperties(entidadeDTO, entidadeSalvaDTO, "id");
			formaPagamentoService.salvar(entidadeSalvaDTO);
		}
	return ResponseEntity.ok().build();
	}
	
	@ExceptionHandler({ FormaPagamentoException.class })
	public ResponseEntity<Object> FormaPagamentoException(FormaPagamentoException ex) {
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
