package br.com.zup.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class NovoUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<NovoUsuarioResponse> cadastrar(@RequestBody @Valid NovoUsuarioRequest request) {
        Usuario usuario = request.toModel();
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().body(new NovoUsuarioResponse(usuario));
    }

}
