package br.com.zup.mercadolivre.produto.opiniao;

public class OpinioesResponse {

    private Integer nota;
    private String titulo;
    private String descricao;
    private String usuario;

    public OpinioesResponse(OpiniaoProduto opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuario = opiniao.getUsuario().getLogin();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuario() {
        return usuario;
    }
}
