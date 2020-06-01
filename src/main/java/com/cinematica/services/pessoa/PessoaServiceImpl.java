package com.cinematica.services.pessoa;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.PessoaException;
import com.cinematica.framework.util.Utils;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.PessoaMapper;
import com.cinematica.model.Agenda;
import com.cinematica.model.Pessoa;
import com.cinematica.model.Usuario;
import com.cinematica.repository.agenda.AgendaRepository;
import com.cinematica.repository.fluxoCaixa.FluxoCaixaRepository;
import com.cinematica.repository.pessoa.PessoaRepository;
import com.cinematica.repository.usuario.UsuarioRepository;

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
    private FluxoCaixaRepository FluxoCaixaRepository;
    @Autowired
    private PessoaMapper mapper;

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

	public PessoaDTO buscarPorId(Integer id) throws PessoaException {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		PessoaDTO pessoaDTO = mapper.toPessoaDTO(obj.orElseThrow(() -> new PessoaException()));
		return pessoaDTO;
	}

    public PessoaDTO salvar(PessoaDTO entidade) throws PessoaException {
    	regrasNegocioSalvar(entidade);
        Pessoa pessoa = pessoaRepository.save(mapper.toPessoa(entidade));
        return mapper.toPessoaDTO(pessoa);
    }

    public void delete(PessoaDTO entidade) throws PessoaException {
    	regrasNegocioExcluir(entidade);
        pessoaRepository.delete(mapper.toPessoa(entidade));
    }
    
    private void regrasNegocioSalvar(PessoaDTO entidade) throws PessoaException {
    	verificarCamposObrigatorios(entidade);
    }

    private void regrasNegocioExcluir(PessoaDTO entidade) throws PessoaException{
    	verificarSeExisteAgenda(entidade);
    	verificarSeExisteFluxoCaixa(entidade);
    	verificaSeExisteUsuario(entidade);
	}

	private void verificarSeExisteAgenda(PessoaDTO entidade) {
		Agenda agenda = this.agendaRepository.consultarAgendaPorPaciente(entidade.getId());
		if (VerificadorUtil.naoEstaNulo(agenda)) {
			throw new PessoaException("erro_impossivel_excluir_paciente_existe_agenda_marcada_para_esse_paciente");
		}
	}

	private void verificaSeExisteUsuario(PessoaDTO entidade) {
		Usuario usuario = this.usuarioRepository.consultarUsuarioPorPessoa(entidade.getId());
		if (VerificadorUtil.naoEstaNulo(usuario)) {
			throw new PessoaException("existem_usuarios_que_utilizam_este_paciente");
		}
	}

	private void verificarSeExisteFluxoCaixa(PessoaDTO entidade) throws PessoaException {
		if (VerificadorUtil.colecaoNaoEstaNulaNemVazio(this.FluxoCaixaRepository.consultarFluxoPorPacienteId(entidade.getId()))) {
			throw new PessoaException("erro_impossivel_excluir_paciente_existe_lancamentos_para_o_paciente");
		}
	}
	
	public void verificarCamposObrigatorios(PessoaDTO entidade) throws PessoaException {
		StringBuffer msg = new StringBuffer();
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getTipoPessoa(), "erro_tipo_pessoa_nao_pode_ser_nulo;"));
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getNome(), "erro_nome_não_pode_ser_nulo;"));
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getSexo(), "erro_voce_deve_escolher_entre_masculino_ou_feminino_o_campo_nao_pode_ser_nulo;"));
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getEmail(), "erro_o_email_nao_pode_ser_nulo;"));
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getProfissao(), "erro_profissao_nao_pode_ser_nula;"));
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getDataNascimento(), "erro_data_nascimento_nao_pode_ser_nula;"));
		
		if (msg.length() > 0) {
			throw new PessoaException(msg.toString());
		}
	}
}