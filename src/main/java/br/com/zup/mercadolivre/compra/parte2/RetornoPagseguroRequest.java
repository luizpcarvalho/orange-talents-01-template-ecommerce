package br.com.zup.mercadolivre.compra.parte2;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.RetornoGatewayPagamento;
import br.com.zup.mercadolivre.compra.parte1.StatusCompra;
import br.com.zup.mercadolivre.config.validacao.ExistId;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    @ExistId(domainClass = Transacao.class, fieldName = "id",
            message = "Já existe uma transação com esse id.")
    private String idTransacao;
    @NotNull
    @Enumerated
    private StatusCompra status;

    public String getIdTransacao() {
        return idTransacao;
    }

    public StatusCompra getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
