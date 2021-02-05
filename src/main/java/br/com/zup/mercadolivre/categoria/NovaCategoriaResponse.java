package br.com.zup.mercadolivre.categoria;

public class NovaCategoriaResponse {

    private Long id;
    private String nome;
    private String categoriaMae;

    public NovaCategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoriaMae = categoria.getCategoriaMae().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoriaMae() {
        return categoriaMae;
    }
}
