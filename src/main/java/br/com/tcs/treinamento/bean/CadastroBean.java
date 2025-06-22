package br.com.tcs.treinamento.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.PrimeFaces;

@ManagedBean(name="cadastroBean")
@ViewScoped
public class CadastroBean implements Serializable {

    private String nome;
    private Integer idade;
    private String email;
    private Date data;
    private String tipoDocumento; // "CPF" ou "CNPJ"
    private String numeroCPF;
    private String numeroCNPJ;

    // Propriedade para armazenar as mensagens de erro
    private String errorMessage;

    // Getters e setters para todos os atributos...

    public void limpar() {
        nome = null;
        idade = null;
        email = null;
        data = null;
        tipoDocumento = null;
        numeroCPF = null;
        numeroCNPJ = null;
    }

    public void validarCampos() {
        List<String> erros = new ArrayList<>();

        if (nome == null || nome.trim().isEmpty()) {
            erros.add("Nome não informado.");
        }
        if (idade == null) {
            erros.add("Idade não informada.");
        }
        if (email == null || email.trim().isEmpty()) {
            erros.add("E-mail não informado.");
        }
        if (data == null) {
            erros.add("Data de nascimento não informada.");
        }
        if (tipoDocumento == null || tipoDocumento.trim().isEmpty()) {
            erros.add("Tipo de documento não informado.");
        } else {
            if ("CPF".equals(tipoDocumento)) {
                if (numeroCPF == null || numeroCPF.trim().isEmpty() || numeroCPF.trim().length() < 11) {
                    erros.add("CPF não informado ou incompleto (deve conter 11 dígitos).");
                }
            } else if ("CNPJ".equals(tipoDocumento)) {
                if (numeroCNPJ == null || numeroCNPJ.trim().isEmpty() || numeroCNPJ.trim().length() < 14) {
                    erros.add("CNPJ não informado ou incompleto (deve conter 14 dígitos).");
                }
            }
        }

        if (!erros.isEmpty()) {
            // Concatena as mensagens de erro (pode usar <br/> para quebra de linha, já que escape="false" no XHTML)
            errorMessage = String.join("<br/>", erros);
            // Exibe o diálogo de erro
            PrimeFaces.current().executeScript("PF('errorDialog').show();");
        } else {
            // Se não houver erros, exibe o diálogo de confirmação
            PrimeFaces.current().executeScript("PF('confirmDialog').show();");
        }
    }

    public void confirmar() {
        // Neste método, pode tratar a persistência dos dados.
        System.out.println("CONFIRMAÇÃO:");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Email: " + email);
        System.out.println("Data: " + data);
        System.out.println("Tipo: " + tipoDocumento);
        System.out.println("CPF: " + numeroCPF);
        System.out.println("CNPJ: " + numeroCNPJ);
    }

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getNumeroCPF() { return numeroCPF; }
    public void setNumeroCPF(String numeroCPF) { this.numeroCPF = numeroCPF; }

    public String getNumeroCNPJ() { return numeroCNPJ; }
    public void setNumeroCNPJ(String numeroCNPJ) { this.numeroCNPJ = numeroCNPJ; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}