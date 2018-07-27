package unit.br.com.banctec.modelo

import spock.lang.Specification

// Toda classe de teste deve terminar com "Test"
// Para que ela use o Spock, deve estender spock.lang.Specification
class TesteLokoTest extends Specification {

    def 'soma deveria funcionar'() {
        given:
        def numero1 = 10
        def numero2 = 20

        when:
        def soma = numero1 + numero2

        then:
        soma == 30
    }

    def 'deveria passar para caixa alta'() {
        given:
        def frase = 'é nóis, mano'

        when:
        def res = frase.toUpperCase()

        then:
        res == 'É NÓIS, MANO'
    }
}
