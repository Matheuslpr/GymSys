package dev.matheus.service;

import dev.matheus.dto.AlunoRequest;
import dev.matheus.dto.AlunoResponse;
import dev.matheus.model.Aluno;
import dev.matheus.repository.AlunoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public AlunoResponse cadastrar(AlunoRequest request) {
        if (request.email() != null && repository.existsByEmail(request.email())) {
            throw new RuntimeException("Já existe aluno cadastrado com esse email");
        }
        Aluno aluno = request.toEntity();
        Aluno alunoSalvo = repository.save(aluno);
        return AlunoResponse.fromEntity(alunoSalvo);
    }

    public Page<AlunoResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(AlunoResponse::fromEntity);
    }

    public AlunoResponse buscarPorId(Long id) {
        Aluno aluno = buscarEntidadePorId(id);
        return AlunoResponse.fromEntity(aluno);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request) {
        Aluno aluno = buscarEntidadePorId(id);
        request.preencher(aluno);
        Aluno alunoAtualizado = repository.save(aluno);
        return AlunoResponse.fromEntity(alunoAtualizado);
    }

    public void excluir(Long id) {
        Aluno aluno = buscarEntidadePorId(id);
        repository.delete(aluno);
    }

    private Aluno buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
