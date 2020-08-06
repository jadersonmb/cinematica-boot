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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.PessoaException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.services.pessoa.PessoaFilterDTO;
import com.cinematica.services.pessoa.PessoaService;

/**
 * pessoaResource
 */
@RestController
@RequestMapping(value = "/pacientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity<?> listarTodos(Pageable pageable, PessoaFilterDTO filter) {
        Page<PessoaDTO> pessoas =  pessoaService.listarTodos(pageable, filter);
        return ResponseEntity.ok().body(pessoas);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Integer id) {
        PessoaDTO entidade = pessoaService.buscarPorId(id);
        return ResponseEntity.ok().body(entidade);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PessoaDTO entidade) {
        PessoaDTO pessoa = pessoaService.salvar(entidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        PessoaDTO entidadeDTO = pessoaService.buscarPorId(id);
        pessoaService.delete(entidadeDTO);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO entidadeSalvaDTO = pessoaService.buscarPorId(id);
		if(VerificadorUtil.naoEstaNulo(entidadeSalvaDTO.getId())) {
			BeanUtils.copyProperties(pessoaDTO, entidadeSalvaDTO, "id");
			pessoaService.salvar(entidadeSalvaDTO);
		}
		return ResponseEntity.ok().build();
	}

    @ExceptionHandler({PessoaException.class})
	public ResponseEntity<Object> EspecialidadeException(PessoaException ex) {
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