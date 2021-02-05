package br.com.zup.mercadolivre.produto.novo;

import br.com.zup.mercadolivre.produto.caracteristica.Caracteristicas;
import br.com.zup.mercadolivre.produto.caracteristica.CaracteristicasRequest;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.config.validacao.ExistId;
import br.com.zup.mercadolivre.config.validacao.NotRepeated;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private Integer quantidade;
    @Size(min = 3, message = "Deve ter pelo menos três características.")
    @NotRepeated(message = "Não podem haver duas ou mais características com o mesmo nome.")
    @Valid
    private List<CaracteristicasRequest> caracteristicas;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @ExistId(domainClass = Categoria.class, fieldName = "id",
            message = "Não existe categoria cadastrada com esse id.")
    @NotNull
    private Long idCategoria;

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicasRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Produto toModel(EntityManager manager, Usuario usuario) {
        Produto produto = new Produto(this.nome, this.valor, this.quantidade,
                this.descricao, manager.find(Categoria.class, this.idCategoria), usuario);
        if(!this.caracteristicas.isEmpty()) {
            List<Caracteristicas> caracteristicas = associaCaracteristicas(this.caracteristicas, produto);
            produto.associaCaracteristicas(caracteristicas);
        }
        return produto;
    }

    public List<Caracteristicas> associaCaracteristicas(List<CaracteristicasRequest> list, Produto produto) {
        List<Caracteristicas> caracteristicas = new ArrayList<>();
        this.caracteristicas.forEach(c -> {
            caracteristicas.add(c.toModel(produto));
        });
        return caracteristicas;
    }

}
