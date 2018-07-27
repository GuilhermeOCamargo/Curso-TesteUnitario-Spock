package br.com.bandtec.boletimapi.repository;

import br.com.bandtec.boletimapi.entity.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author José Yoshiriro
 */
public interface BoletimRepository extends CrudRepository<Boletim, Long> {

    boolean existsByIdAndToken(Long id, String token);

    List<Boletim> findByTokenOrderByAluno(String token);

    Boletim findByIdAndToken(Long id, String token);

    List<Boletim> findByAlunoLikeAndTokenOrderByAluno(String aluno, String token);

    void deleteByIdAndToken(Long id, String token);
}
