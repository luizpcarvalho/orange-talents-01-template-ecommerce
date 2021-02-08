package br.com.zup.mercadolivre.compra.parte1;

import br.com.zup.mercadolivre.compra.parte2.StatusTransacao;

public enum StatusCompra {
    INICIADA,
    SUCESSO,
    FALHA;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.sucesso;
        } else {
            return StatusTransacao.falha;
        }
    }
}
