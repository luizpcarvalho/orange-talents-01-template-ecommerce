package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.compra.parte1.GatewayPagamento;
import br.com.zup.mercadolivre.compra.parte1.StatusCompra;
import br.com.zup.mercadolivre.compra.parte2.RetornoPagseguroRequest;
import br.com.zup.mercadolivre.compra.parte2.Transacao;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

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

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", usuario=" + usuario +
                ", gateway=" + gateway +
                ", status=" + status +
                ", instanteCriacao=" + instanteCriacao +
                ", transacoes=" + transacoes +
                '}';
    }

    public void adicionaTransacao(RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Já existe uma transação igual a essa processada. "+novaTransacao.toString());

        Set<Transacao> transacoesConcluidasComSucesso = transacoesConcluidasComSucesso();
        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluída com sucesso.");

        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso).collect(Collectors.toSet());
        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,
                "Mais de uma transação concluída com sucesso na compra " + this.id);
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
