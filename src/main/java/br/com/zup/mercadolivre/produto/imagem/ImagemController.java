package br.com.zup.mercadolivre.produto.imagem;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ImagemController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UploaderFake uploaderFake;

    @PostMapping("/produto/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest request,
                                             @AuthenticationPrincipal Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, id);
        if(produto == null) {
            return ResponseEntity.notFound().build();
        }
        if(!produto.pertenceAoUsuario(usuario)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "O usuário " + usuario.getLogin() + " não tem permissão para alterar esse produto");
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        entityManager.merge(produto);

        return ResponseEntity.ok().build();
    }

}
