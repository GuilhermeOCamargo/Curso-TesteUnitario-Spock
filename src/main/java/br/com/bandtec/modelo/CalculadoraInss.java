package br.com.bandtec.modelo;

public class CalculadoraInss {


    public double calcularInss(double salario){
        if(salario > 0 && salario <= 1693.72){
            return salario * 0.08;
        }
        if(salario >= 1693.73 && salario <= 2822.90){
            return salario * 0.09;
        }
        if(salario >=2822.91){
            return salario * 0.11;
        }else{
            return 0;
        }
    }

}
