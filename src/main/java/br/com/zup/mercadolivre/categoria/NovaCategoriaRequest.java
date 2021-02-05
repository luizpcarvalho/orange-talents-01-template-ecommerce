package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.config.validacao.ExistId;
import br.com.zup.mercadolivre.config.validacao.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome",
            message = "Já existe uma categoria cadastrada com esse nome.")
    private String nome;
    @ExistId(domainClass = Categoria.class, fieldName = "id",
            message = "Essa categoria não está cadastrada.")
    @Positive
    private Long idCategoriaMae;

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepositoy repository) {
        Categoria categoria = new Categoria(this.nome);
        if(this.idCategoriaMae != null) {
            Categoria categoriaMae = repository.getOne(this.idCategoriaMae);
            Assert.notNull(categoriaMae, "O id da categoria mãe precisa ser válido.");
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }

}
