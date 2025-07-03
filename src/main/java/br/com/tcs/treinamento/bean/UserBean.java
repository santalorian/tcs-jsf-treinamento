package br.com.tcs.treinamento.bean;

import br.com.tcs.treinamento.model.User;
import br.com.tcs.treinamento.service.usuario.UserServiceClient;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    private List<User> usuarios;

    @PostConstruct
    public void init() {
        UserServiceClient client = new UserServiceClient();
        usuarios = client.listarUsuarios();
    }

    public List<User> getUsuarios() {
        return usuarios;
    }
}