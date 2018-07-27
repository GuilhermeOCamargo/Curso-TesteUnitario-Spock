package br.com.bandtec.modelo;

public class CalculadoraInss {

    public double calcularInss(double salario) {
        if (salario<=1693.72) {
            return salario*0.08;
        } else if (salario<=2822.90) {
            return salario*0.09;
        } else {
            return salario*0.11;
        }
    }
}