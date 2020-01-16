package com.cinematica;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import com.cinematica.model.Pessoa;
import com.cinematica.model.SimNao;
import com.cinematica.model.Usuario;
import com.cinematica.service.PessoaService;
import com.cinematica.service.UsuarioService;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UsuarioTest
 */
public class UsuarioTest extends DemoApplicationTests {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PessoaService pessoaService;


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