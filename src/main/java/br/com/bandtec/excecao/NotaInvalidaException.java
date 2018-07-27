package br.com.bandtec.excecao;

public class NotaInvalidaException extends RuntimeException {

    public NotaInvalidaException(double nota) {
        this.nota = nota;
    }

    private double nota;


    @Override
    public String getMessage() {
        return "Nota deve ser entre 0 e 10. Chegou: "+ nota;
    }
}
