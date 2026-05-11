package dev.matheus.repository;

import dev.matheus.model.FaturaMatricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturaMatriculaRepository extends JpaRepository<FaturaMatricula, Long> {
}
