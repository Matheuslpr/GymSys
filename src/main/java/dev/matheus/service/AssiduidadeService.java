package dev.matheus.service;

import dev.matheus.dto.AssiduidadeRequest;
import dev.matheus.dto.AssiduidadeResponse;
import dev.matheus.exception.RegraNegocioException;
import dev.matheus.model.Assiduidade;
import dev.matheus.model.Matricula;
import dev.matheus.repository.AssiduidadeRepository;
import dev.matheus.repository.MatriculaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssiduidadeService {

    private final AssiduidadeRepository repository;
    private final MatriculaRepository matriculaRepository;

    public AssiduidadeService(AssiduidadeRepository repository, MatriculaRepository matriculaRepository) {
        this.repository = repository;
        this.matriculaRepository = matriculaRepository;
    }

    public AssiduidadeResponse cadastrar(AssiduidadeRequest request) {
        Assiduidade assiduidade = request.toEntity();
        assiduidade.setMatricula(buscarMatriculaPorId(request.matriculaId()));
        Assiduidade assiduidadeSalva = repository.save(assiduidade);
        return AssiduidadeResponse.fromEntity(assiduidadeSalva);
    }

    public Page<AssiduidadeResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(AssiduidadeResponse::fromEntity);
    }

    public AssiduidadeResponse buscarPorId(Long id) {
        Assiduidade assiduidade = buscarEntidadePorId(id);
        return AssiduidadeResponse.fromEntity(assiduidade);
    }

    public AssiduidadeResponse atualizar(Long id, AssiduidadeRequest request) {
        Assiduidade assiduidade = buscarEntidadePorId(id);
        request.preencher(assiduidade);
        assiduidade.setMatricula(buscarMatriculaPorId(request.matriculaId()));
        Assiduidade assiduidadeAtualizada = repository.save(assiduidade);
        return AssiduidadeResponse.fromEntity(assiduidadeAtualizada);
    }

    public void excluir(Long id) {
        Assiduidade assiduidade = buscarEntidadePorId(id);
        repository.delete(assiduidade);
    }

    private Assiduidade buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioException("Assiduidade não encontrada"));
    }

    private Matricula buscarMatriculaPorId(Long id) {
        return matriculaRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Matrícula não encontrada"));
    }
}

