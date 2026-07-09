package dev.matheus.service;

import dev.matheus.dto.FaturaMatriculaRequest;
import dev.matheus.dto.FaturaMatriculaResponse;
import dev.matheus.exception.RegraNegocioException;
import dev.matheus.model.FaturaMatricula;
import dev.matheus.model.Matricula;
import dev.matheus.repository.FaturaMatriculaRepository;
import dev.matheus.repository.MatriculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FaturaMatriculaService {
    private final FaturaMatriculaRepository repository;
    private final MatriculaRepository matriculaRepository;

    public FaturaMatriculaService(FaturaMatriculaRepository repository, MatriculaRepository matriculaRepository) {
        this.repository = repository;
        this.matriculaRepository = matriculaRepository;
    }

    public FaturaMatriculaResponse cadastrar(FaturaMatriculaRequest request) {
        FaturaMatricula faturaMatricula = request.toEntity();
        faturaMatricula.setMatricula(buscarMatriculaPorId(request.matriculaId()));
        FaturaMatricula faturaSalva = repository.save(faturaMatricula);
        return FaturaMatriculaResponse.fromEntity(faturaSalva);
    }

    public Page<FaturaMatriculaResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(FaturaMatriculaResponse::fromEntity);
    }

    public FaturaMatriculaResponse buscarPorId(Long id) {
        FaturaMatricula faturaMatricula = buscarEntidadePorId(id);
        return FaturaMatriculaResponse.fromEntity(faturaMatricula);
    }

    public FaturaMatriculaResponse atualizar(Long id, FaturaMatriculaRequest request) {
        FaturaMatricula faturaMatricula = buscarEntidadePorId(id);
        request.preencher(faturaMatricula);
        faturaMatricula.setMatricula(buscarMatriculaPorId(request.matriculaId()));
        FaturaMatricula faturaAtualizada = repository.save(faturaMatricula);
        return FaturaMatriculaResponse.fromEntity(faturaAtualizada);
    }

    public void excluir(Long id) {
        FaturaMatricula faturaMatricula = buscarEntidadePorId(id);
        repository.delete(faturaMatricula);
    }

    private FaturaMatricula buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioException("Fatura não encontrada"));
    }

    private Matricula buscarMatriculaPorId(Long id) {
        return matriculaRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Matrícula não encontrada"));
    }
}
