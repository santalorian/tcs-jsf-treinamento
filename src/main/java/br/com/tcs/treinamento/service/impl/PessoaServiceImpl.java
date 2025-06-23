package br.com.tcs.treinamento.service.impl;

import br.com.tcs.treinamento.dao.PessoaDAO;
import br.com.tcs.treinamento.entity.Pessoa;
import br.com.tcs.treinamento.service.PessoaService;

import java.util.List;

public class PessoaServiceImpl implements PessoaService {

    private PessoaDAO pessoaDAO = new PessoaDAO();

    @Override
    public void cadastrar(Pessoa pessoa) {
        pessoaDAO.cadastrar(pessoa);
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        return pessoaDAO.buscarPorId(id);
    }

    @Override
    public List<Pessoa> listar() {
        return pessoaDAO.listar();
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        pessoaDAO.atualizar(pessoa);
    }

    @Override
    public void excluir(Pessoa pessoa) {
        pessoaDAO.excluir(pessoa);
    }
}