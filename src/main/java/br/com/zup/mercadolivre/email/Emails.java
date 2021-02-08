package br.com.zup.mercadolivre.email;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.EventoCompraSucesso;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.pergunta.PerguntaProduto;
import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class Emails implements EventoCompraSucesso {

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

    public void falhaPagamento(Compra compra, UriComponentsBuilder uriBuilder) {
        mailer.send("Falha no pagamento. Tente novamente com o link a seguir: "
                        + compra.getGateway().criaUrlRetorno(compra, uriBuilder),
                "Falha no pagamento", compra.getUsuario().getLogin(),
                "novacompra@nossomercadolivre.com", compra.getProduto().getUsuario().getLogin());
    }

    @Override
    public void processa(Compra compra) {
        mailer.send("Pagamento realizado com sucesso. " + compra.toString(),
                "Pagamento realizado com sucesso", compra.getUsuario().getLogin(),
                "novacompra@nossomercadolivre.com", compra.getProduto().getUsuario().getLogin());
    }
}
