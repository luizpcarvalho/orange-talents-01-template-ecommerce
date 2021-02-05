package br.com.zup.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;
import java.util.Objects;

public class PerguntasResponse {

    private String titulo;
    private String descricao;
    private LocalDateTime instanteCriacao;
    private String usuario;
    private String produto;

    public PerguntasResponse(String titulo, String descricao, LocalDateTime instanteCriacao,
                             String usuario, String produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.instanteCriacao = instanteCriacao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerguntasResponse that = (PerguntasResponse) o;
        return titulo.equals(that.titulo) && descricao.equals(that.descricao) && instanteCriacao.equals(that.instanteCriacao) && usuario.equals(that.usuario) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, instanteCriacao, usuario, produto);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getProduto() {
        return produto;
    }
}
