package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasResponse;
import br.com.zup.mercadolivre.produto.opiniao.OpinioesResponse;
import br.com.zup.mercadolivre.produto.pergunta.PerguntasResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ProdutoDetalheResponse {

    private List<String> linksImagens;
    private String nome;
    private BigDecimal preco;
    private List<CaracteristicasResponse> caracteristicas;
    private String descricao;
    private Double mediaNotas;
    private Long numeroTotalNotas;
    private List<OpinioesResponse> opinioes;
    private Set<PerguntasResponse> perguntas;

    public ProdutoDetalheResponse(Produto produto) {
        this.linksImagens = produto.linksImagens();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.listaCaracteristicas();
        this.descricao = produto.getDescricao();
        this.mediaNotas = produto.mediaNotas();
        this.numeroTotalNotas = produto.numeroTotalNotas();
        this.opinioes = produto.listaOpinioes();
        this.perguntas = produto.listaPerguntas();
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<CaracteristicasResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Long getNumeroTotalNotas() {
        return numeroTotalNotas;
    }

    public List<OpinioesResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntasResponse> getPerguntas() {
        return perguntas;
    }
}
