package br.com.tcs.treinamento.bean;

import jakarta.faces.view.ViewScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "cadastroBean")
@ViewScoped
public class CadastroBean implements Serializable {

    private String nome;
    private Integer idade;
    private String email;
    private Date data;
    private String tipoDocumento;
    private String numeroCPF;
    private String numeroCNPJ;

    public void salvar() {
        String documento = null;
        if ("CPF".equals(tipoDocumento)) {
            documento = getNumeroCPF();
        } else if ("CNPJ".equals(tipoDocumento)) {
            documento = getNumeroCNPJ();
        }
        System.out.println("Salvando: " + nome + ", " + idade + ", " + email + ", " + data + ", " + tipoDocumento + ": " + documento);
    }

    public void limpar() {
        setNome(null);
        setIdade(null);
        setEmail(null);
        setData(null);
        setTipoDocumento(null);
        setNumeroCNPJ(null);
        setNumeroCPF(null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroCPF() {
        return numeroCPF;
    }

    public void setNumeroCPF(String numeroCPF) {
        this.numeroCPF = numeroCPF;
    }

    public String getNumeroCNPJ() {
        return numeroCNPJ;
    }

    public void setNumeroCNPJ(String numeroCNPJ) {
        this.numeroCNPJ = numeroCNPJ;
    }
}