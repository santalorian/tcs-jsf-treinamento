package br.com.tcs.treinamento.bean;

import br.com.tcs.treinamento.entity.Pessoa;
import br.com.tcs.treinamento.service.PessoaService;
import br.com.tcs.treinamento.service.impl.PessoaServiceImpl;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "consultaPessoaBean")
@ViewScoped
public class ConsultaPessoaBean implements Serializable {

    private List<Pessoa> pessoas;
    private Pessoa pessoaSelecionada;
    private String errorMessage;
    private Long pessoaId;
    private Boolean tpManutencao;

    private transient PessoaService pessoaService = new PessoaServiceImpl();

    @PostConstruct
    public void init() {
        // Recupera parâmetro "pessoaId" da URL
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
        String idParam = params.get("pessoaId");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                pessoaId = Long.valueOf(idParam);
                pessoaSelecionada = pessoaService.buscarPorId(pessoaId);
            } catch (NumberFormatException e) {
                errorMessage = "ID inválido da pessoa.";
            }
        }
        // Recupera o parâmetro tpManutencao; se não existir, assume um valor padrão (por exemplo, true para edição)
        String tpParam = params.get("tpManutencao");
        if (tpParam != null && !tpParam.trim().isEmpty()) {
            setTpManutencao(Boolean.valueOf(tpParam));
        } else {
            setTpManutencao(true);
        }
        pessoas = pessoaService.listar();
    }

    public String prepararEdicao(Pessoa pessoa) {
        this.pessoaSelecionada = pessoa;
        return "alterar?faces-redirect=true&pessoaId=" + pessoa.getId() + "&tpManutencao=true";
    }

    public String prepararExclusao(Pessoa pessoa) {
        this.pessoaSelecionada = pessoa;
        return "excluir?faces-redirect=true&pessoaId=" + pessoa.getId() + "&tpManutencao=false";
    }

    public String atualizarConsulta() {
        pessoaService.atualizar(pessoaSelecionada);
        pessoas = pessoaService.listar();
        return "consultaPessoas?faces-redirect=true";
    }

    public void limparAlteracoes() {
        if (pessoaSelecionada != null) {
            pessoaSelecionada = pessoaService.buscarPorId(pessoaSelecionada.getId());
        }
    }

    /**
     * Método que converte o VO para a entidade e chama o service para persistir.
     * Após persistir, exibe o popup de sucesso.
     */
    public void confirmar() {
        // Converte o VO para a entidade Pessoa
        Pessoa pessoa = mapPessoaEntity();
        // Chama o service para persistir a entidade
        try {
            pessoaService.atualizar(pessoa);
            // Exibe o popup de sucesso após a confirmação
            PrimeFaces.current().executeScript("PF('successDialog').show();");
        } catch (Exception e) {
            // Em caso de erro na persistência, exibe o diálogo de erro
            errorMessage = "Erro ao cadastrar pessoa: " + e.getMessage();
            PrimeFaces.current().executeScript("PF('errorDialog').show();");
            return;
        }
    }

    /**
     * mapPessoaEntity
     * Mapeamento da VO para Entity
     */
    private Pessoa mapPessoaEntity() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaSelecionada.getId());
        pessoa.setNome(pessoaSelecionada.getNome());
        pessoa.setIdade(pessoaSelecionada.getIdade());
        pessoa.setEmail(pessoaSelecionada.getEmail());
        pessoa.setData(pessoaSelecionada.getData());
        pessoa.setTipoDocumento(pessoaSelecionada.getTipoDocumento());
        pessoa.setNumeroCPF(pessoaSelecionada.getNumeroCPF());
        pessoa.setNumeroCNPJ(pessoaSelecionada.getNumeroCNPJ());
        pessoa.setDataManutencao(new Date());
        pessoa.setAtivo(getTpManutencao());
        return pessoa;
    }

    public void confirmarExclusao(){
        Pessoa pessoa = mapPessoaEntity();
        try {
            pessoaService.atualizar(pessoa); //Exclusao logica
            //pessoaService.excluir(pessoa); // Exclusao fisica
            // Exibe o popup de sucesso após a confirmação
            PrimeFaces.current().executeScript("PF('successDialog').show();");
        } catch (Exception e) {
            // Em caso de erro na persistência, exibe o diálogo de erro
            errorMessage = "Erro ao cadastrar pessoa: " + e.getMessage();
            PrimeFaces.current().executeScript("PF('errorDialog').show();");
            return;
        }
    }

    public void validarCampos() {
        List<String> erros = new ArrayList<>();

        if (pessoaSelecionada.getNome() == null || pessoaSelecionada.getNome().trim().isEmpty()) {
            erros.add("Nome não informado.");
        }
        if (pessoaSelecionada.getIdade() == null) {
            erros.add("Idade não informada.");
        }
        if (pessoaSelecionada.getEmail() == null || pessoaSelecionada.getEmail().trim().isEmpty()) {
            erros.add("E-mail não informado.");
        }
        if (pessoaSelecionada.getData() == null) {
            erros.add("Data de nascimento não informada.");
        }
        if (pessoaSelecionada.getTipoDocumento() == null || pessoaSelecionada.getTipoDocumento().trim().isEmpty()) {
            erros.add("Tipo de documento não informado.");
        } else {
            if ("CPF".equals(pessoaSelecionada.getTipoDocumento())) {
                if (pessoaSelecionada.getNumeroCPF() == null || pessoaSelecionada.getNumeroCPF().trim().isEmpty() ||
                        pessoaSelecionada.getNumeroCPF().trim().length() < 11) {
                    erros.add("CPF não informado ou incompleto (deve conter 11 dígitos).");
                }
            } else if ("CNPJ".equals(pessoaSelecionada.getTipoDocumento())) {
                if (pessoaSelecionada.getNumeroCNPJ() == null || pessoaSelecionada.getNumeroCNPJ().trim().isEmpty() ||
                        pessoaSelecionada.getNumeroCNPJ().trim().length() < 14) {
                    erros.add("CNPJ não informado ou incompleto (deve conter 14 dígitos).");
                }
            }
        }

        if (!erros.isEmpty()) {
            errorMessage = String.join("<br/>", erros);
            PrimeFaces.current().executeScript("PF('errorDialog').show();");
        } else {
            PrimeFaces.current().executeScript("PF('confirmDialog').show();");
        }
    }

    public void exportarPdf() {
        System.out.println("Implementar metodo para PDF");
    }

    public void exportarExcel() {
        System.out.println("Implementar metodo para Excel");
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public PessoaService getPessoaService() {
        return pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public Boolean getTpManutencao() {
        return tpManutencao;
    }

    public void setTpManutencao(Boolean tpManutencao) {
        this.tpManutencao = tpManutencao;
    }
}