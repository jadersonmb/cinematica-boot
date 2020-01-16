package com.cinematica.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.PessoaException;
import com.cinematica.model.Pessoa;
import com.cinematica.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * pessoaResource
 */
@RestController
@RequestMapping(value = "/paciente")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listarTodos() {
        List<Pessoa> pessoas =  pessoaService.listarTodos();
        return ResponseEntity.ok().body(pessoas);
    }   

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable Integer id) {
        Pessoa entidade = pessoaService.buscarPorId(id);
        PessoaDTO dto = pessoaService.toDTO(entidade);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@RequestBody Pessoa entidade) {
        Pessoa pessoa = pessoaService.salvar(entidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
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