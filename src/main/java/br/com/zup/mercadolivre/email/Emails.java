package br.com.zup.mercadolivre.email;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.pergunta.PerguntaProduto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    @Autowired
    private Mailer mailer;

    public void novaPergunta(PerguntaProduto pergunta) {
        mailer.send("<html>...</html>", "Nova pergunta...", pergunta.getUsuario().getLogin(),
                "novapergunta@nossomercadolivre.com", pergunta.getProduto().getUsuario().getLogin());
    }

    public void novaCompra(Usuario usuario, Produto produto) {
        mailer.send("<html>...</html>", "Nova compra...", usuario.getLogin(),
                "novacompra@nossomercadolivre.com", produto.getUsuario().getLogin());
    }
}
