package br.com.zup.mercadolivre.compra.parte2;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.RetornoGatewayPagamento;
import br.com.zup.mercadolivre.config.validacao.ExistId;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    @ExistId(domainClass = Transacao.class, fieldName = "id",
            message = "Já existe uma transação com esse id.")
    private String idTransacao;

    public RetornoPaypalRequest(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.falha:StatusTransacao.sucesso;

        return new Transacao(statusCalculado, idTransacao, compra);
    }
}
