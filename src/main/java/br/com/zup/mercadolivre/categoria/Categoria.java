package br.com.zup.mercadolivre.categoria;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria CategoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setCategoriaMae(Categoria CategoriaMae) {
        this.CategoriaMae = CategoriaMae;
    }

    public Categoria getCategoriaMae() {
        return CategoriaMae;
    }
}
