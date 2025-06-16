package br.com.tcs.treinamento.model;

import java.io.Serializable;

public class UsuarioVO implements Serializable {
    private static final long serialVersionUID = 1L;;

    private String nomeCompleto;
    private Integer idade;
    private String email;
    private Integer tipoDocumento;
    private String documento;
    private String cpf;
    private String cnpj;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public UsuarioVO(String nomeCompleto, Integer idade, String email, Integer tipoDocumento, String documento, String cpf, String cnpj) {
        this.nomeCompleto = nomeCompleto;
        this.idade = idade;
        this.email = email;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    public UsuarioVO() {
    }
}
