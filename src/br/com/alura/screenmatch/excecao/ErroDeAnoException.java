package br.com.alura.screenmatch.excecao;

public class ErroDeAnoException extends RuntimeException {
    private String mensagem;

    public ErroDeAnoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
