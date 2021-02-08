package br.com.zup.mercadolivre.compra;

/**
 * Interface para eventos de uma nova compra
 */
public interface EventoCompraSucesso {

    void processa(Compra compra);

}
