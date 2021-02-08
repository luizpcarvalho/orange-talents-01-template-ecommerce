package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.email.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucessos;
    @Autowired
    private Emails emails;

    public void processa(Compra compra, UriComponentsBuilder uriBuilder) {

        if(compra.processadaComSucesso()) {
            eventosCompraSucessos.forEach(evento -> evento.processa(compra));
        } else {
            emails.falhaPagamento(compra, uriBuilder);
        }

    }

}
