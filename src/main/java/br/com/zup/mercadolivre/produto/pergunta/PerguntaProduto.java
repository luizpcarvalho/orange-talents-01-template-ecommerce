package br.com.zup.mercadolivre.produto.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PerguntaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private LocalDateTime instanteCriacao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public PerguntaProduto() {
    }

    public PerguntaProduto(String titulo, String descricao, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.instanteCriacao = LocalDateTime.now();
        this.usuario = usuario;
        this.produto = produto;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
