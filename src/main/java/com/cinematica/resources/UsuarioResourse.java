package com.cinematica.resources;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.cinematica.exception.CinematicaExceptionHandler.Erro;
import com.cinematica.exception.UsuarioException;
import com.cinematica.model.Usuario;
import com.cinematica.service.UsuarioService;

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

/**
 * UsuarioResourse
 */
@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioResourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MessageSource messageSource;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listarTodos() {
        List<Usuario> usuario =  usuarioService.listarTodos();
        return ResponseEntity.ok().body(usuario);
    }   

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Usuario entidade = usuarioService.consultarPorId(id);
        return ResponseEntity.ok().body(entidade);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@RequestBody Usuario entidade) {
        Usuario usuario = usuarioService.salvar(entidade);
        return ResponseEntity.ok().body(usuario);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Usuario entidade = usuarioService.consultarPorId(id);
        usuarioService.delete(entidade);
        return ResponseEntity.ok().build();
    }
    
    @ExceptionHandler({UsuarioException.class})
	public ResponseEntity<Object> EspecialidadeException(UsuarioException ex) {
		String mensagemUsuario = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
    
}