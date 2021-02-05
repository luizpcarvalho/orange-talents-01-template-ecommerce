package br.com.zup.mercadolivre.produto.caracteristica;

public class CaracteristicasResponse {

    private Long id;
    private String nome;
    private String valor;

    public CaracteristicasResponse(Caracteristicas caracteristicas) {
        this.id = caracteristicas.getId();
        this.nome = caracteristicas.getNome();
        this.valor = caracteristicas.getValor();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }
}
