package br.com.zup.mercadolivre.outrossistemas;

import javax.validation.constraints.NotNull;

public class NovaCompraNFRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNFRequest{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
