package com.cinematica.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.PessoaException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.PessoaMapper;
import com.cinematica.model.Agenda;
import com.cinematica.model.Pessoa;
import com.cinematica.model.Usuario;
import com.cinematica.repository.AgendaRepository;
import com.cinematica.repository.PessoaRepository;
import com.cinematica.repository.UsuarioRepository;

/**
 * PessoaService
 */
@Service
public class PessoaServiceImpl implements PessoaService, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private PessoaMapper mapper;

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Integer id) throws PessoaException {
        Optional<Pessoa> obj =  pessoaRepository.findById(id);
        return obj.orElseThrow(() -> new PessoaException("Pessoa não Encontrada"));
    }

    public void salvarAll(List<Pessoa> listaPessoas) {
        pessoaRepository.saveAll(listaPessoas);
    }

    public PessoaDTO salvar(Pessoa entidade) {
        Pessoa pessoa = pessoaRepository.save(entidade);
        return mapper.toPessoaDTO(pessoa);
    }

    public void delete(Pessoa entidade) throws PessoaException {
    	regrasNegocioExcluir(entidade);
        pessoaRepository.delete(entidade);
    }

    private void regrasNegocioExcluir(Pessoa entidade) throws PessoaException{
    	verificarSeExisteAgenda(entidade);
    	verificarSeExisteFluxoCaixa(entidade);
    	verificaSeExisteUsuario(entidade);
	}

	private void verificarSeExisteAgenda(Pessoa entidade) {
		Agenda agenda = this.agendaRepository.consultarAgendaPorPaciente(entidade.getId());
		if (VerificadorUtil.naoEstaNulo(agenda)) {
			throw new PessoaException("erro_impossivel_excluir_paciente_existe_agenda_marcada_para_esse_paciente");
		}
	}

	private void verificaSeExisteUsuario(Pessoa entidade) {
		Usuario usuario = this.usuarioRepository.consultarUsuarioPorPessoa(entidade.getId());
		if (VerificadorUtil.naoEstaNulo(usuario)) {
			throw new PessoaException("existem_usuarios_que_utilizam_este_paciente");
		}
	}

	private void verificarSeExisteFluxoCaixa(Pessoa entidade) throws PessoaException {
		if (VerificadorUtil.colecaoNaoEstaNulaNemVazio(this.pessoaRepository.consultarFluxoPorPacienteId(entidade.getId()))) {
			throw new PessoaException("erro_impossivel_excluir_paciente_existe_lancamentos_para_o_paciente");
		}
	}

	public PessoaDTO toDTO (Pessoa entidade){
        return mapper.toPessoaDTO(entidade);
    }
}