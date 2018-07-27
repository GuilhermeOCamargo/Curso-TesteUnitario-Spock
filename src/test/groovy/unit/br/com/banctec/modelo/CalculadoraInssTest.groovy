package unit.br.com.banctec.modelo

import br.com.bandtec.modelo.CalculadoraInss
import spock.lang.Specification

class CalculadoraInssTest extends Specification{

    def 'deveria retornar tudo positivo'() {
        expect:
        new CalculadoraInss().calcularInss(salario) == resultado

        where:
        salario | resultado
        1000	|	80
        1693.72	|	salario * 0.08
        1693.73	|	salario * 0.09
        2000	|	180
        2822.90	|	salario * 0.09
        2822.91	|	salario * 0.11
        5000	|	550
    }

}
