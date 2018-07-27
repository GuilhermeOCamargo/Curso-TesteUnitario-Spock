package unit.br.com.bandtec.boletimapi.controller

import br.com.bandtec.boletimapi.controllers.BoletimController
import br.com.bandtec.boletimapi.entity.Boletim
import br.com.bandtec.boletimapi.repository.BoletimRepository
import spock.lang.Specification

class BoletimControllerTest extends Specification{

    def 'deveria trazer um Boletim ou 404 no get' () {
        given:
        BoletimController controller = new BoletimController()

        //criando o Mock
        BoletimRepository repositoryMock = Mock(BoletimRepository)
        repositoryMock.findByIdAndToken(1, _) >> new Boletim()

        controller.repository = repositoryMock

        when:
        def encontrado = controller.getUm(1, 'achei')

        then:
        encontrado.statusCodeValue == 200
        encontrado.body instanceof Boletim

        when:
        def naoencontrado = controller.getUm(0, 'achei')

        then:
        naoencontrado.statusCodeValue == 404
        naoencontrado.body == null
    }

    def 'deveria criar ou retornar 400'() {
        given:
        Boletim bValido = new Boletim(id: 1)
        Boletim bInvalido = new Boletim(id: -80)
        def msgErro = 'Deu ruim, código negativo não rola!'
        BoletimController controller = new BoletimController()

        //criando o Mock
        BoletimRepository repositoryMock = Mock(BoletimRepository)
        repositoryMock.save(bInvalido) >> {throw new Exception(msgErro)}
        /*repositoryMock.save(bValido) >> 201*/

        controller.repository = repositoryMock

        when:
        def invalido = controller.criar(bInvalido, 'aaa')

        then:
        invalido.statusCodeValue == 400
        invalido.body == msgErro
        when:
        def valido = controller.criar(bValido, 'aaa')

        then:
        valido.statusCodeValue == 201
    }

}
