package dev.matheus.repository;

import dev.matheus.model.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {
}
