package br.com.zup.mercadolivre.outrossistemas;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.EventoCompraSucesso;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "Compra não processada com sucesso. " + compra.toString());

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idComprador", compra.getUsuario().getId());

        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }

}
