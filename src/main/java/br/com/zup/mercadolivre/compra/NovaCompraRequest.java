package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.config.validacao.ExistId;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull
    @ExistId(domainClass = Produto.class, fieldName = "id",
            message = "O produto não existe.")
    private Long idProduto;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        return new Compra(produto, this.quantidade, usuario, this.gateway);
    }
}
