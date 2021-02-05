package br.com.zup.mercadolivre.produto.pergunta;

import br.com.zup.mercadolivre.email.Emails;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Set;

@RestController
public class PerguntaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Emails emails;

    @PostMapping("/produto/{id}/perguntas")
    @Transactional
    public ResponseEntity<Set<PerguntasResponse>> adicionar(@PathVariable("id") Long id,
                                                             @RequestBody @Valid NovaPerguntaRequest request,
                                                             @AuthenticationPrincipal Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, id);
        if(produto == null) {
            return ResponseEntity.notFound().build();
        }
        if(!produto.pertenceAoUsuario(usuario)) {
            PerguntaProduto novaPergunta = request.toModel(usuario, produto);
            produto.associaPergunta(novaPergunta);
            entityManager.merge(produto);

            emails.novaPergunta(novaPergunta);

            Set<PerguntasResponse> perguntas = produto.listaPerguntas();
            return ResponseEntity.ok().body(perguntas);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "O dono do produto não pode perguntar sobre o próprio produto.");
    }

}
