package br.com.zup.mercadolivre.usuario;

import java.time.LocalDateTime;

public class NovoUsuarioResponse {

    private Long id;
    private String login;
    private String senha;
    private LocalDateTime instanteCadastro;

    public NovoUsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.instanteCadastro = usuario.getInstanteCadastro();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }
}
