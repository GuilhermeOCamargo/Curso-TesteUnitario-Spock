package br.com.bandtec.excecoes;

public class NotaInvalidaException
        extends RuntimeException {

    private double nota;

    public NotaInvalidaException(double nota) {
        this.nota = nota;
    }

    @Override
    public String getMessage() {
        return
                "Nota deve estar entre 0 e 10. Chegou "+nota;
    }
}