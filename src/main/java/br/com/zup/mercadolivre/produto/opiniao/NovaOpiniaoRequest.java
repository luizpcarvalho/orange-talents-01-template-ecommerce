package br.com.zup.mercadolivre.produto.opiniao;

import br.com.zup.mercadolivre.config.validacao.ExistId;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.validation.constraints.*;

public class NovaOpiniaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @NotNull
    @ExistId(domainClass = Produto.class, fieldName = "id",
            message = "O produto não existe.")
    private Long idProduto;

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    @Override
    public String toString() {
        return "NovaOpiniaoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idProduto=" + idProduto +
                '}';
    }

    public OpiniaoProduto toModel(Usuario usuario, Produto produto) {
        return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
    }
}
