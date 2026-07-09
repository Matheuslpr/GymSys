package dev.matheus.repository;

import dev.matheus.model.MatriculaModalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaModalidadeRepository extends JpaRepository<MatriculaModalidade, Long> {

    boolean existsByMatriculaIdAndModalidadeId(Long matriculaId, Long modalidadeId);
    boolean existsByMatriculaIdAndModalidadeIdAndIdNot(Long matriculaId, Long modalidadeId, Long id);
}
