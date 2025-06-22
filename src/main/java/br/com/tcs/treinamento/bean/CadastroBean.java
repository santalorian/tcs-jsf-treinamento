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
    private String numeroDocumento;

    public void salvar() {
        // lógica para salvar
        System.out.println("Salvando: " + nome + ", " + tipoDocumento + ": " + numeroDocumento);
    }

    public void limpar() {
        setNome(null);
        setIdade(null);
        setEmail(null);
        setData(null);
        setTipoDocumento(null);
        setNumeroDocumento(null);
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}