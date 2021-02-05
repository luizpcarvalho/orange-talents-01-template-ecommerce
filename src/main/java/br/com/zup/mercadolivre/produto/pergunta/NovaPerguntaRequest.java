package br.com.zup.mercadolivre.produto.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "NovaPerguntaRequest{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao +
                '}';
    }

    public PerguntaProduto toModel(Usuario usuario, Produto produto) {
        return new PerguntaProduto(this.titulo, this.descricao, usuario, produto);
    }
}
