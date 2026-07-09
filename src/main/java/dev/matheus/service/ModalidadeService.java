package dev.matheus.service;

import dev.matheus.dto.ModalidadeRequest;
import dev.matheus.dto.ModalidadeResponse;
import dev.matheus.exception.RegraNegocioException;
import dev.matheus.model.Modalidade;
import dev.matheus.repository.ModalidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ModalidadeService {

    private final ModalidadeRepository repository;

    public ModalidadeService(ModalidadeRepository repository) {
        this.repository = repository;
    }

    public ModalidadeResponse cadastrar(ModalidadeRequest request) {
        Modalidade modalidade = request.toEntity();
        Modalidade modalidadeSalva = repository.save(modalidade);
        return ModalidadeResponse.fromEntity(modalidadeSalva);
    }

    public Page<ModalidadeResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(ModalidadeResponse::fromEntity);
    }

    public ModalidadeResponse buscarPorId(Long id) {
        Modalidade modalidade = buscarEntidadePorId(id);
        return ModalidadeResponse.fromEntity(modalidade);
    }

    public ModalidadeResponse atualizar(Long id, ModalidadeRequest request) {
        Modalidade modalidade = buscarEntidadePorId(id);
        request.preencher(modalidade);
        Modalidade modalidadeAtualizada = repository.save(modalidade);
        return ModalidadeResponse.fromEntity(modalidadeAtualizada);
    }

    public void excluir(Long id) {
        Modalidade modalidade = buscarEntidadePorId(id);
        repository.delete(modalidade);
    }

    private Modalidade buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioException("Modalidade não encontrada"));
    }
}

