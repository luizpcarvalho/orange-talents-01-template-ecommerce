package br.com.zup.mercadolivre.compra.parte1;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.email.Emails;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compra")
public class CompraParte1Controller {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Emails emails;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid NovaCompraRequest request, @AuthenticationPrincipal Usuario usuario,
                                  UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produto = entityManager.find(Produto.class, request.getIdProduto());
        boolean abateu = produto.abateEstoque(request.getQuantidade());
        if(abateu) {
            Compra novaCompra = request.toModel(produto, usuario);
            produto.associaCompra(novaCompra);
            entityManager.persist(novaCompra);

            emails.novaCompra(usuario, produto);

            return ResponseEntity.ok().body(novaCompra.getGateway().criaUrlRetorno(novaCompra, uriComponentsBuilder));

        }

        BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
        problemaComEstoque.rejectValue("quantidade", null,
                "Não foi possível realizar a compra por conta do estoque.");

        throw problemaComEstoque;
    }

}
