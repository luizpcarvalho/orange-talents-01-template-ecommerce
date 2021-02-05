package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @Column(nullable = false)
    private Integer quantidade;
    @ManyToOne
    private Usuario usuario;
    @Column(nullable = false)
    private GatewayPagamento gateway;
    @Column(nullable = false)
    private StatusCompra status;
    @Column(nullable = false)
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Compra() {
    }

    public Compra(Produto produto, Integer quantidade, Usuario usuario, GatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.gateway = gateway;
        this.status = StatusCompra.INICIADA;
        this.instanteCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public GatewayPagamento getGateway() {
        return this.gateway;
    }
}
