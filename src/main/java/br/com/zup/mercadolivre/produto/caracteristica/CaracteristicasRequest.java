package br.com.zup.mercadolivre.produto.caracteristica;

import br.com.zup.mercadolivre.produto.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicasRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String valor;

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicasRequest that = (CaracteristicasRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Caracteristicas toModel(Produto produto) {
        return new Caracteristicas(this.nome, this.valor, produto);
    }
}
