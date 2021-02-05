package br.com.zup.mercadolivre.config.handler;

public class ErrosDeValidacaoResponse {

    private String campo;
    private String mensagem;

    public ErrosDeValidacaoResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
