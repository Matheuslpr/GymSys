package dev.matheus.service;

import dev.matheus.dto.MatriculaRequest;
import dev.matheus.dto.MatriculaResponse;
import dev.matheus.exception.RegraNegocioException;
import dev.matheus.model.Aluno;
import dev.matheus.model.Matricula;
import dev.matheus.model.enums.StatusMatricula;
import dev.matheus.repository.AlunoRepository;
import dev.matheus.repository.MatriculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    private final MatriculaRepository repository;
    private final AlunoRepository alunoRepository;

    public MatriculaService(MatriculaRepository repository, AlunoRepository alunoRepository) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
    }

    public MatriculaResponse cadastrar(MatriculaRequest request) {
        if (repository.existsByAlunoIdAndStatus(request.alunoId(), StatusMatricula.ATIVA)) {
            throw new RegraNegocioException("Aluno já possui uma matrícula ativa");
        }
        Matricula matricula = request.toEntity();
        matricula.setAluno(buscarAlunoPorId(request.alunoId()));
        Matricula matriculaSalva = repository.save(matricula);
        return MatriculaResponse.fromEntity(matriculaSalva);
    }

    public Page<MatriculaResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(MatriculaResponse::fromEntity);
    }

    public MatriculaResponse buscarPorId(Long id) {
        Matricula matricula = buscarEntidadePorId(id);
        return MatriculaResponse.fromEntity(matricula);
    }

    public MatriculaResponse atualizar(Long id, MatriculaRequest request) {
        Matricula matricula = buscarEntidadePorId(id);
        request.preencher(matricula);
        matricula.setAluno(buscarAlunoPorId(request.alunoId()));
        Matricula matriculaAtualizada = repository.save(matricula);
        return MatriculaResponse.fromEntity(matriculaAtualizada);
    }

    public void excluir(Long id) {
        Matricula matricula = buscarEntidadePorId(id);
        repository.delete(matricula);
    }

    private Matricula buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioException("Matrícula não encontrada"));
    }

    private Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Aluno não encontrado"));
    }
}