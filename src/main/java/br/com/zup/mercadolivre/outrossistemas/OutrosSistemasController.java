package br.com.zup.mercadolivre.outrossistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping("/notas-fiscais")
    public void criaNota(@RequestBody @Valid NovaCompraNFRequest request) throws InterruptedException {
        System.out.println("Criando nota para " + request.toString());
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody @Valid RankingNovaCompraRequest request) throws InterruptedException {
        System.out.println("Criando ranking para " + request.toString());
        Thread.sleep(150);
    }

}
