package unit.br.com.banctec.modelo

import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import org.apache.http.HttpResponse
import spock.lang.Specification

class BoletimApiTest extends Specification{

    def urlBase = 'http://10.0.0.23:8080/boletins'

    def 'deveria trazer um boletim válido'() {
        given:
        def cliente = new RESTClient("${urlBase}/3", ContentType.JSON)

        when:
        HttpResponse resposta = cliente.get(headers:['token':'Guilherme'])

        then:
        resposta.status == 200

        and:
        /*resposta.data.id == 3
        resposta.data.aluno instanceof String
        resposta.data.nota1 instanceof Number*/
        resposta.data.id == 3
        new BoletimModelo(resposta.data)
    }

    def 'deveria trazer 404 para id inválido'() {
        given:
        def cliente = new RESTClient("${urlBase}/0", ContentType.JSON)

        when:
        HttpResponse resposta = cliente.get(headers:['token':'Guilherme'])

        then:
        def ex = thrown(HttpResponseException)
        ex.statusCode == 404
    }
    def 'deveria excluir um boletim inexistente'() {
        given:
        def cliente = new RESTClient("${urlBase}/120")

        when:
        HttpResponse resposta = cliente.delete(headers:['token':'Guilherme'])

        then:
        def ex = thrown(HttpResponseException)
        ex.statusCode == 404

    }

    def 'tentar excluir um registro existente'() {
        given:
        def cliente = new RESTClient("${urlBase}/31", ContentType.JSON)

        when:
        HttpResponse resposta = cliente.delete(headers:['token':'Guilherme'])

        then:
       resposta.status == 200
    }

}
