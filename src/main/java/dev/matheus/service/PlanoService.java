package dev.matheus.service;

import dev.matheus.dto.PlanoRequest;
import dev.matheus.dto.PlanoResponse;
import dev.matheus.exception.RegraNegocioException;
import dev.matheus.model.Modalidade;
import dev.matheus.model.Plano;
import dev.matheus.repository.ModalidadeRepository;
import dev.matheus.repository.PlanoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {

    private final PlanoRepository repository;
    private final ModalidadeRepository modalidadeRepository;

    public PlanoService(PlanoRepository repository, ModalidadeRepository modalidadeRepository) {
        this.repository = repository;
        this.modalidadeRepository = modalidadeRepository;
    }

    public PlanoResponse cadastrar(PlanoRequest request) {
        Plano plano = request.toEntity();
        plano.setModalidade(buscarModalidadePorId(request.modalidadeId()));
        Plano planoSalvo = repository.save(plano);
        return PlanoResponse.fromEntity(planoSalvo);
    }

    public Page<PlanoResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(PlanoResponse::fromEntity);
    }

    public PlanoResponse buscarPorId(Long id) {
        Plano plano = buscarEntidadePorId(id);
        return PlanoResponse.fromEntity(plano);
    }

    public PlanoResponse atualizar(Long id, PlanoRequest request) {
        Plano plano = buscarEntidadePorId(id);
        request.preencher(plano);
        plano.setModalidade(buscarModalidadePorId(request.modalidadeId()));
        Plano planoAtualizado = repository.save(plano);
        return PlanoResponse.fromEntity(planoAtualizado);
    }

    public void excluir(Long id) {
        Plano plano = buscarEntidadePorId(id);
        repository.delete(plano);
    }

    private Plano buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioException("Plano não encontrado"));
    }

    private Modalidade buscarModalidadePorId(Long id) {
        return modalidadeRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Modalidade não encontrada"));
    }
}
