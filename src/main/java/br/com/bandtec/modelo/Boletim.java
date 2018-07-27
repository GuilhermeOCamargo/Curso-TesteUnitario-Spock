package br.com.bandtec.modelo;

import br.com.bandtec.excecao.FrequenciaInvalidaException;
import br.com.bandtec.excecao.NotaInvalidaException;

public class Boletim {

    private double nota1;
    private double nota2;
    private double frequencia;

    public double getMedia() {
        return (nota1 + nota2) / 2;
    }

    private void validarFrequencia(){
        if (this.frequencia<0 || this.frequencia>100) {
            throw new FrequenciaInvalidaException();
        }
    }

    private void validarNota(double nota){
        if (nota<0 || nota>10) {
            throw new NotaInvalidaException(nota1);
        }
    }
    public String getResultado() {
        validarFrequencia();
        validarNota(this.nota1);
        validarNota(this.nota2);
        boolean aprovadoMedia = this.getMedia() >= 5;
        boolean aprovadoFrequencia = this.frequencia >= 75;

        if (!aprovadoMedia && !aprovadoFrequencia) {
            return "Reprovado geral";
        }

        if (!aprovadoMedia) {
            return "Reprovado por nota";
        }

        if (!aprovadoFrequencia) {
            return "Reprovado por frequência";
        }

        return "Aprovado";
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }

}
