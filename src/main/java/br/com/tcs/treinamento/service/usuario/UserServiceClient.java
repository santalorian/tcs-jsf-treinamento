package br.com.tcs.treinamento.service.usuario;

import br.com.tcs.treinamento.model.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserServiceClient {

    private static final String BASE_URL = "http://localhost:8090";

    public void salvarUsuario(User user) {
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = "/usuario/salvar";
        restTemplate.postForObject(BASE_URL + endpoint, user, Void.class);
    }

    public List<User> listarUsuarios() {
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = "/usuario/buscar";

        ResponseEntity<List<User>> response = restTemplate.exchange(
                BASE_URL + endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );
        return response.getBody();
    }
    public User buscarPorId(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = "/usuario/" + id;
        return restTemplate.getForObject(BASE_URL + endpoint, User.class);
    }

    public void deletarUsuario(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = "/usuario/" + id;
        restTemplate.delete(BASE_URL + endpoint);
    }
}