package dev.matheus.repository;

import dev.matheus.model.FaturaMatricula;
import dev.matheus.projection.AlunosPorCidadeProjection;
import dev.matheus.projection.FaturamentoMensalProjection;
import dev.matheus.projection.FaturasEmAbertoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RelatorioAcademiaRepository extends JpaRepository<FaturaMatricula,Long > {

    @Query(
        value = """
               SELECT 
                       TO_CHAN(data_vencimento, 'YYYY-MM') AS mes,
                       SUM(valor) as total
               FROM  fatura_matriculas
               WHERE status = 'PAGA'
               GROUP BY TO_CHAR(data_vencimento, 'YYYY-MM') 
               ORDER BY mes
               """,
            nativeQuery = true
    )
    List<FaturamentoMensalProjection> faturamentoMensal();


    @Query(
            value = """
               SELECT 
                       cidade,
                       count(*) as quantidade
               FROM  alunos
               GROUP BY cidade
               ORDER BY quantidade desc
               """,
            nativeQuery = true
    )
    List<AlunosPorCidadeProjection> alunosPorCidade();


    @Query(
            value = """
               SELECT 
                       cidade,
                       count(*) as quantidade
               FROM  fatura_matriculas 
               JOIN matriculas m ON m.id - f.matricula_id
               JOIN alunos a ON a.id = m.aluno_id
               WHERE f.status = 'ABERTA'
               ORDER BY f.data_vencimento desc
               """,
            nativeQuery = true
    )
    List<FaturasEmAbertoProjection> faturasEmAberto();

}
