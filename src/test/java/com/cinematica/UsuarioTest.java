package com.cinematica;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import com.cinematica.model.Pessoa;
import com.cinematica.model.SimNao;
import com.cinematica.model.Usuario;
import com.cinematica.service.PessoaServiceImpl;
import com.cinematica.service.UsuarioServiceImpl;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UsuarioTest
 */
@Ignore
public class UsuarioTest extends CinematicaApplicationTests {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private PessoaServiceImpl pessoaService;


    @Test
    @Ignore
    public void testSalvarNovoUsuario() {
        Pessoa entidadePessoa = pessoaService.buscarPorId(32);

        Usuario entidade = new Usuario();
        entidade.setAtivo(SimNao.Sim);
        entidade.setLogin("teste-unit");
        entidade.setSenha("senha");
        entidade.setTokenAcesso("senha");
        entidade.setPessoa(entidadePessoa);
        entidade.setUltimoAcesso(new Date());
        entidade.setId(entidadePessoa.getId());

        Usuario entidadeSalva = usuarioService.salvar(entidade);
        assertNotNull(entidadeSalva.getId());
    }
}