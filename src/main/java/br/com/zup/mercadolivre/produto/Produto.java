package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.produto.caracteristica.Caracteristicas;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasResponse;
import br.com.zup.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zup.mercadolivre.produto.opiniao.OpiniaoProduto;
import br.com.zup.mercadolivre.produto.opiniao.OpinioesResponse;
import br.com.zup.mercadolivre.produto.pergunta.PerguntaProduto;
import br.com.zup.mercadolivre.produto.pergunta.PerguntasResponse;
import br.com.zup.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    @Min(0)
    private Integer quantidade;
    @Column(nullable = false)
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario usuario;
    @Column(nullable = false)
    private LocalDateTime instanteCadastro;
    @Column(nullable = false)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Caracteristicas> caracteristicas = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<OpiniaoProduto> opinioes = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<PerguntaProduto> perguntas = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Compra> compras = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade,
                   String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.instanteCadastro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public List<OpinioesResponse> listaOpinioes() {
        List<OpinioesResponse> opinioes = new ArrayList<>();
        this.opinioes.forEach(o -> opinioes.add(new OpinioesResponse(o)));
        return opinioes;
    }

    public List<CaracteristicasResponse> listaCaracteristicas() {
        List<CaracteristicasResponse> caracteristicas = new ArrayList<>();
        this.caracteristicas.forEach(c -> caracteristicas.add(new CaracteristicasResponse(c)));
        return caracteristicas;
    }

    public List<String> linksImagens() {
        List<String> imagens = new ArrayList<>();
        this.imagens.forEach(i -> imagens.add(i.getLink()));
        return imagens;
    }

    public Set<PerguntasResponse> listaPerguntas() {
        Set<PerguntasResponse> perguntas = new HashSet<>();
        this.perguntas.forEach(p -> {
            perguntas.add(new PerguntasResponse(p.getTitulo(), p.getDescricao(), p.getInstanteCriacao(),
                    p.getUsuario().getLogin(), p.getProduto().getNome()));
        });
        return perguntas;
    }

    public Double mediaNotas() {
        List<Integer> notas = new ArrayList<>();
        opinioes.forEach(o -> notas.add(o.getNota()));
        IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
        OptionalDouble media = mapToInt.average();
        if(media.isPresent()){
            return media.getAsDouble();
        } else {
            return 0.0;
        }
    }

    public Long numeroTotalNotas() {
        List<Integer> notas = new ArrayList<>();
        opinioes.forEach(o -> notas.add(o.getNota()));
        IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
        return mapToInt.count();
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public void associaCaracteristicas(List<Caracteristicas> caracteristicas) {
        this.caracteristicas.addAll(caracteristicas);
    }

    public boolean pertenceAoUsuario(Usuario usuario) {
        return this.usuario.equals(usuario);
    }

    public void associaOpiniao(OpiniaoProduto opiniao) {
        this.opinioes.add(opiniao);
    }

    public void associaPergunta(PerguntaProduto pergunta) {
        this.perguntas.add(pergunta);
    }

    public void associaCompra(Compra novaCompra) {
        this.compras.add(novaCompra);
    }

    public boolean abateEstoque(@Positive Integer quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para " +
                "abater o estoque " + quantidade);
        if(this.quantidade >= quantidade){
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", instanteCadastro=" + instanteCadastro +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                ", opinioes=" + opinioes +
                ", perguntas=" + perguntas +
                '}';
    }
}
