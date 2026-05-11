package dev.matheus.repository;

import dev.matheus.model.Graduacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduacaoRepository extends JpaRepository<Graduacao, Long> {
}
