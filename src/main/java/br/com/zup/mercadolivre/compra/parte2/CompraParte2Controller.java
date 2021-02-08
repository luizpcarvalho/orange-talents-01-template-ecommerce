package br.com.zup.mercadolivre.compra.parte2;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.EventosNovaCompra;
import br.com.zup.mercadolivre.compra.RetornoGatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class CompraParte2Controller {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagseguro(@PathVariable("id") Long id, @RequestBody RetornoPagseguroRequest request,
                                         UriComponentsBuilder uriBuilder) {
        return processa(id, request, uriBuilder);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long id, @RequestBody RetornoPaypalRequest request,
                                      UriComponentsBuilder uriBuilder) {
        return processa(id, request, uriBuilder);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento request, UriComponentsBuilder uriBuilder) {
        Compra compra = entityManager.find(Compra.class, idCompra);
        compra.adicionaTransacao(request);

        entityManager.merge(compra);

        eventosNovaCompra.processa(compra, uriBuilder);

        return compra.toString();
    }

}
