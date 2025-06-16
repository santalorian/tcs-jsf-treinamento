package br.com.tcs.treinamento.bean;

import br.com.tcs.treinamento.model.UsuarioVO;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="apresentacaoBean")
@SessionScoped
public class ApresentacaoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UsuarioVO usuarioVO= new UsuarioVO();

    public UsuarioVO getUsuarioVO() {
        return usuarioVO;
    }

    public void setUsuarioVO(UsuarioVO usuarioVO) {
        this.usuarioVO = usuarioVO;
    }

    public String validarApresentacao() {
        return "";
    }
}
