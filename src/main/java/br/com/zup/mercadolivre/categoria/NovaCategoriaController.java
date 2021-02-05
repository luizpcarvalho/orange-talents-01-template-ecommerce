package br.com.zup.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class NovaCategoriaController {

    @Autowired
    private CategoriaRepositoy categoriaRepositoy;

    @PostMapping
    @Transactional
    public ResponseEntity<NovaCategoriaResponse> cadastrar(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel(categoriaRepositoy);
        categoriaRepositoy.save(categoria);

        return ResponseEntity.ok().body(new NovaCategoriaResponse(categoria));
    }

}
