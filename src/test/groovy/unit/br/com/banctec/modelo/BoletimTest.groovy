package unit.br.com.banctec.modelo

import br.com.bandtec.excecao.FrequenciaInvalidaException
import br.com.bandtec.excecao.NotaInvalidaException
import br.com.bandtec.modelo.Boletim
import spock.lang.Specification

class BoletimTest extends Specification {

    def 'deveria ser aprovado'() {
        given:
        Boletim boletim = new Boletim(nota1: 5, nota2: 7, frequencia: 75)

        when:
        def resultado = boletim.getResultado()

        then:
        resultado == 'Aprovado'
    }

    def 'deveria ser reprovado por nota'() {
        given:
        Boletim boletim = new Boletim(nota1: 4.99, nota2: 4.99, frequencia: 75)

        when:
        def res = boletim.getResultado()

        then:
        res == 'Reprovado por nota'
    }

    def 'deveria ser reprovado por frequência'() {

    }

    def 'deveria ser reprovado geral'() {

    }

    def 'deveria lançar uma exceção p/ frequência inválida'() {
        given:
        Boletim boletim = new Boletim(frequencia: -0.01)

        when:
        boletim.getResultado()

        then:
        thrown(FrequenciaInvalidaException)
    }

    def 'deveria lancar uma exceção p/ nota inválida'() {
        given:
        Boletim nota1menor = new Boletim(nota1: -0.01)
        Boletim nota1maior = new Boletim(nota1: 10.1)
        Boletim nota2menor = new Boletim(nota2: -0.01)
        Boletim nota2maior = new Boletim(nota2: 10.01)

        when:
        nota1menor.getResultado()

        then:
        thrown(NotaInvalidaException)

        when:
        nota1maior.getResultado()

        then:
        thrown(NotaInvalidaException)

        when:
        nota2menor.getResultado()

        then:
        thrown(NotaInvalidaException)

        when:
        nota2maior.getResultado()

        then:
        thrown(NotaInvalidaException)
    }

   /* def 'deveria trazer a mensagem de erro p/ nota'() {
        given:
        Boletim bnota1 = new Boletim(nota1: 10.01)
        Boletim bnota2 = new Boletim(nota2: -0.01)

        when:
        bnota1.getResultado()

        then:
        def erro = thrown(NotaInvalidaException)
        erro.getMessage() == "Nota deve ser entre 0 e 10. Chegou: ${bnota1.nota1}"

        when:
        bnota2.getResultado()

        then:
        erro = thrown(NotaInvalidaException)
        erro.getMessage() == "Nota deve ser entre 0 e 10. Chegou: ${bnota2.nota2}"
    }*/

    def 'deveria ter o resultado correto p/ valores válidos'() {
        expect:
        new Boletim(nota1: nota1, nota2: nota2, frequencia: frequencia).getResultado() == resultado

        /*Data Driven Test*/
        where:
        nota1 | nota2 | frequencia | resultado
        5     |5      |75          | 'Aprovado'
        5     |5      |74          | 'Reprovado por frequência'
        3     |3      |75          | 'Reprovado por nota'
        2     |2      |50          | 'Reprovado geral'

    }

    def 'deveria validar a frequencia'() {
        given:
        Boletim bvalido = new Boletim(frequencia: 50)
        Boletim binvalido = new Boletim(frequencia: -0.01)
        Boletim binvalido2 = new Boletim(frequencia: 101)
        when:
        bvalido.validarFrequencia()

        then:
        true

        when:
        binvalido.validarFrequencia()

        then:
        thrown(FrequenciaInvalidaException)

        when:
        binvalido2.validarFrequencia()

        then:
        thrown(FrequenciaInvalidaException)
    }

    def 'deveria validar a nota'() {
        given:
        Boletim boletim = new Boletim();

        when:
        boletim.validarNota(-0.01)

        then:
        thrown(NotaInvalidaException)

        when:
        boletim.validarNota(5)

        then:
        true

        when:
        boletim.validarNota(11)

        then:
        thrown(NotaInvalidaException)
    }
}
