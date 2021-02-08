package br.com.zup.mercadolivre.produto.caracteristica;

import br.com.zup.mercadolivre.produto.Produto;

import javax.persistence.*;

@Entity
public class Caracteristicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String valor;
    @ManyToOne
    private Produto produto;

    @Deprecated
    public Caracteristicas() {
    }

    public Caracteristicas(String nome, String valor, Produto produto) {
        this.nome = nome;
        this.valor = valor;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Caracteristicas{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
