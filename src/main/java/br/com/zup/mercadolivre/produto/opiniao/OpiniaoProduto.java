package br.com.zup.mercadolivre.produto.opiniao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;

@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer nota;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public OpiniaoProduto() {
    }

    public OpiniaoProduto(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
