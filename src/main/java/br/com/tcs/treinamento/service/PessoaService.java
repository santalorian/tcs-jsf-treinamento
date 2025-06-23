package br.com.tcs.treinamento.service;

import br.com.tcs.treinamento.entity.Pessoa;
import java.util.List;

public interface PessoaService {
    void cadastrar(Pessoa pessoa);
    Pessoa buscarPorId(Long id);
    List<Pessoa> listar();
    void atualizar(Pessoa pessoa);
    void excluir(Pessoa pessoa);
}