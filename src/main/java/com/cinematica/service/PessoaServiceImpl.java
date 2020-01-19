package com.cinematica.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.PessoaException;
import com.cinematica.interfaces.mapper.PessoaMapper;
import com.cinematica.interfaces.services.PessoaService;
import com.cinematica.model.Pessoa;
import com.cinematica.repository.PessoaRepository;

/**
 * PessoaService
 */
@Service
public class PessoaServiceImpl implements PessoaService, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PessoaRepository pessoaRepository;
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
        return mapper.toPessoaDTO(entidade);
    }
}