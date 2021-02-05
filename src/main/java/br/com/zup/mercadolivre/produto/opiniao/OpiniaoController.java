package br.com.zup.mercadolivre.produto.opiniao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OpiniaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/produto/{id}/opiniao")
    @Transactional
    public ResponseEntity<?> adicionarOpiniao(@PathVariable("id") Long id, @RequestBody @Valid NovaOpiniaoRequest request,
                                              @AuthenticationPrincipal Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, id);
        if(produto == null) {
            return ResponseEntity.notFound().build();
        }
        if(!produto.pertenceAoUsuario(usuario)) {
            OpiniaoProduto opiniao = request.toModel(usuario, produto);
            produto.associaOpiniao(opiniao);
            entityManager.merge(produto);

            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O dono do produto não pode opinar sobre o mesmo.");
    }

}
