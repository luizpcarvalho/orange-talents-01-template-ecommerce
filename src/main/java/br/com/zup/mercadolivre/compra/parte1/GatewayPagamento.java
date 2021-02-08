package br.com.zup.mercadolivre.compra.parte1;

import br.com.zup.mercadolivre.compra.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {
    paypal{
        @Override
        public String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    },
    pagseguro{
        @Override
        public String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return "pagseguro.com?returnId=" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
        }
    };

    public abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
