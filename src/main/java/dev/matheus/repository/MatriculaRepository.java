package dev.matheus.repository;

import dev.matheus.model.Matricula;
import dev.matheus.model.enums.StatusMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByAlunoIdAndStatus(Long alunoId, StatusMatricula status);
}
