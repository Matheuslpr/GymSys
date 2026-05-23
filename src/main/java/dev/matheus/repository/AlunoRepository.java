package dev.matheus.repository;

import dev.matheus.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlunoRepository extends JpaRepository<Aluno, Long> , JpaSpecificationExecutor<Aluno> {
    boolean existsByEmail(String email);
}
