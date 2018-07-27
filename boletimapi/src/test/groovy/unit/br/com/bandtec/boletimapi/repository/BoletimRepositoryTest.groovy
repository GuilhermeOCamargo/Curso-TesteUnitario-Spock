package unit.br.com.bandtec.boletimapi.repository

import br.com.bandtec.boletimapi.entity.Boletim
import br.com.bandtec.boletimapi.repository.BoletimRepository
import spock.lang.Specification

class BoletimRepositoryTest extends Specification{

    def 'deveria criar mocks'() {
        given:
        // criando um Mock da interface BoletimRepository
        def repository = Mock(BoletimRepository)

        //ensinar os comportamentos para o mock
        repository.findByIdAndToken(1, 'a') >> new Boletim(id: 1, aluno: 'Zé')
        repository.findByIdAndToken(22, 'a') >> new Boletim(id: 22, aluno: 'Lady')
        repository.findByIdAndToken(666, _) >> new Boletim(id: 666, aluno: 'Capiroto')

        when:
        def boletim1 = repository.findByIdAndToken(1, 'a')

        then:
        boletim1.id == 1
        boletim1.aluno == 'Zé'

        when:
        def boletim666 = repository.findByIdAndToken(666, 'Qualquer coisa')

        then:
        boletim666.id == 666
        boletim666.aluno == 'Capiroto'

    }
}
