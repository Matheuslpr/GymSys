package dev.matheus.repository;

import dev.matheus.model.HistoricoGraduacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoGraduacaoRepository extends JpaRepository<HistoricoGraduacao, Long> {
    List<HistoricoGraduacao> findByMatriculaModalidadeIdOrderByDataAlteracaoDesc(Long matriculaModalidadeId);
}