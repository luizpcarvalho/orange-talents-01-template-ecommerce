package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.compra.parte2.Transacao;

public interface RetornoGatewayPagamento {
    /**
     *
     * @param compra
     * @return uma nova transação em função do gateway específico
     */
    Transacao toTransacao(Compra compra);
}
