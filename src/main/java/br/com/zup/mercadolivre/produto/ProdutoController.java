package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.produto.novo.NovoProdutoRequest;
import br.com.zup.mercadolivre.produto.novo.NovoProdutoResponse;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<NovoProdutoResponse> cadastrar(@RequestBody @Valid NovoProdutoRequest request,
                                                         @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.toModel(entityManager, usuario);
        entityManager.persist(produto);

        return ResponseEntity.ok().body(new NovoProdutoResponse(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalheResponse> detalhar(@PathVariable("id") Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        return ResponseEntity.ok().body(new ProdutoDetalheResponse(produto));
    }

}
