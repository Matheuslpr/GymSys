package dev.matheus.controller;

import dev.matheus.projection.AlunosPorCidadeProjection;
import dev.matheus.projection.FaturamentoMensalProjection;
import dev.matheus.projection.FaturasEmAbertoProjection;
import dev.matheus.repository.RelatorioAcademiaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioAcademiaController {
    private final RelatorioAcademiaRepository repository;

    public RelatorioAcademiaController(RelatorioAcademiaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/faturamento-mensal")
    public List<FaturamentoMensalProjection> faturamentoMensal() {
        return repository.faturamentoMensal();
    }

    @GetMapping("/alunos-por-cidade")
    public List<AlunosPorCidadeProjection> alunosPorCidade() {
        return repository.alunosPorCidade();
    }

    @GetMapping("/faturamento-em-aberto")
    public List<FaturasEmAbertoProjection> faturasEmAbeto() {
        return repository.faturasEmAberto();
    }

}
