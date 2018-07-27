package unit.br.com.banctec.modelo

import br.com.bandtec.excecoes.FrequenciaInvalidaException
import br.com.bandtec.excecoes.NotaInvalidaException
import br.com.bandtec.modelo.Boletim
import spock.lang.Specification

class BoletimTest extends Specification {

    def 'deveria ter o resultado correto p/ valores válidos'() {
        setup:
        def boletim = new Boletim(nota1: n1, nota2: n2, frequencia: freq)

        expect:
        boletim.getResultado() == resultado

        // Data Driven test
        where:
        n1   | n2   | freq  | resultado
        5    | 5    | 75    | 'Aprovado'
        5    | 5    | 74    | 'Reprovado por frequência'
        3	 | 3	| 75	| 'Reprovado por nota'
        2	 | 2	| 50	| 'Reprovado geral'

    }

    def 'deveria lançar uma exceção p/ frequência inválida'() {
        given:
        Boletim boletim = new Boletim(frequencia: -0.01)

        when:
        boletim.getResultado()

        // verificando se uma determinada exceção ocorreu
        // durante a execução do teste
        then:
        thrown(FrequenciaInvalidaException)
    }

    def 'deveria lançar uma exceção p/ nota inválida'() {
        given:
        Boletim bnota1menor = new Boletim(nota1: -0.01)
        Boletim bnota1maior = new Boletim(nota1: 10.01)
        Boletim bnota2menor = new Boletim(nota2: -0.01)
        Boletim bnota2maior = new Boletim(nota2: 10.01)

        when:
        bnota1menor.getResultado()

        then:
        thrown(NotaInvalidaException)


        when:
        bnota1maior.getResultado()

        then:
        thrown(NotaInvalidaException)


        when:
        bnota2menor.getResultado()

        then:
        thrown(NotaInvalidaException)


        when:
        bnota2maior.getResultado()

        then:
        thrown(NotaInvalidaException)
    }

    def 'deveria trazer a mensagem de erro p/ nota'() {
        given:
        Boletim bnota1 = new Boletim(nota1: 10.01)
        Boletim bnota2 = new Boletim(nota2: -0.01)

        when:
        bnota1.getResultado()

        then:
        def erro = thrown(NotaInvalidaException)
        erro.getMessage() == "Nota deve estar entre 0 e 10. Chegou ${bnota1.nota1}"


        when:
        bnota2.getResultado()

        then:
        erro = thrown(NotaInvalidaException)
        erro.getMessage() == "Nota deve estar entre 0 e 10. Chegou ${bnota2.nota2}"

    }


}