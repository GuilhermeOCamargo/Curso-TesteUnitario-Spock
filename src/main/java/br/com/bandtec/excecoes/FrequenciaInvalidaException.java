package br.com.bandtec.excecoes;

public class FrequenciaInvalidaException
        extends RuntimeException {

    @Override
    public String getMessage() {
        return "Frequência deve estar entre 0 e 100";
    }
}