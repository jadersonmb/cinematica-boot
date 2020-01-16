package com.cinematica.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.PessoaException;
import com.cinematica.interfaces.PessoaMapper;
import com.cinematica.model.Pessoa;
import com.cinematica.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PessoaService
 */
@Service
public class PessoaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PessoaRepository pessoaRepository;
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

    public Pessoa salvar(Pessoa entidade) {
        return pessoaRepository.save(entidade);
    }

    public void delete(Pessoa entidade) throws PessoaException {
        pessoaRepository.delete(entidade);
    }

    public PessoaDTO toDTO (Pessoa entidade){
        return mapper.pessoaToPessoaDTO(entidade);
    }
}