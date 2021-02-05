package br.com.zup.mercadolivre.produto.novo;

import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasResponse;
import br.com.zup.mercadolivre.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NovoProdutoResponse {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private List<CaracteristicasResponse> caracteristicas = new ArrayList<>();
    private String descricao;
    private String categoria;
    private String usuario;
    private LocalDateTime instanteCadastro;

    public NovoProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.caracteristicas = produto.listaCaracteristicas();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria().getNome();
        this.usuario = produto.getUsuario().getLogin();
        this.instanteCadastro = produto.getInstanteCadastro();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicasResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }
}
