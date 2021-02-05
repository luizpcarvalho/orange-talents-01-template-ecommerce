package br.com.zup.mercadolivre.usuario;

import br.com.zup.mercadolivre.config.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @JsonProperty
    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login",
            message = "Já existe um usuário cadastrado com este e-mail.")
    private String login;
    @JsonProperty
    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, this.senha);
    }
}
