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

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.PessoaException;
import com.cinematica.interfaces.services.PessoaService;
import com.cinematica.model.Pessoa;

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
    public ResponseEntity<?> listarTodos() {
        List<Pessoa> pessoas =  pessoaService.listarTodos();
        return ResponseEntity.ok().body(pessoas);
    }   

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Integer id) {
        Pessoa entidade = pessoaService.buscarPorId(id);
        PessoaDTO dto = pessoaService.toDTO(entidade);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Pessoa entidade) {
        Pessoa pessoa = pessoaService.salvar(entidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Pessoa entidade = pessoaService.buscarPorId(id);
        pessoaService.delete(entidade);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({PessoaException.class})
	public ResponseEntity<Object> EspecialidadeException(PessoaException ex) {
		String mensagemUsuario = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}