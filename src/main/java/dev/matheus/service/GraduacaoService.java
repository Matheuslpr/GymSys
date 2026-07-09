package dev.matheus.service;

import dev.matheus.dto.GraduacaoRequest;
import dev.matheus.dto.GraduacaoResponse;
import dev.matheus.exception.RegraNegocioException;
import dev.matheus.model.Graduacao;
import dev.matheus.model.Modalidade;
import dev.matheus.repository.GraduacaoRepository;
import dev.matheus.repository.ModalidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GraduacaoService {

    private final GraduacaoRepository repository;
    private final ModalidadeRepository modalidadeRepository;

    public GraduacaoService(GraduacaoRepository repository, ModalidadeRepository modalidadeRepository) {
        this.repository = repository;
        this.modalidadeRepository = modalidadeRepository;
    }

    public GraduacaoResponse cadastrar(GraduacaoRequest request) {
        Graduacao graduacao = request.toEntity();
        graduacao.setModalidade(buscarModalidadePorId(request.modalidadeId()));
        Graduacao graduacaoSalva = repository.save(graduacao);
        return GraduacaoResponse.fromEntity(graduacaoSalva);
    }

    public Page<GraduacaoResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(GraduacaoResponse::fromEntity);
    }

    public GraduacaoResponse buscarPorId(Long id) {
        Graduacao graduacao = buscarEntidadePorId(id);
        return GraduacaoResponse.fromEntity(graduacao);
    }

    public GraduacaoResponse atualizar(Long id, GraduacaoRequest request) {
        Graduacao graduacao = buscarEntidadePorId(id);
        request.preencher(graduacao);
        graduacao.setModalidade(buscarModalidadePorId(request.modalidadeId()));
        Graduacao graduacaoAtualizada = repository.save(graduacao);
        return GraduacaoResponse.fromEntity(graduacaoAtualizada);
    }

    public void excluir(Long id) {
        Graduacao graduacao = buscarEntidadePorId(id);
        repository.delete(graduacao);
    }

    private Graduacao buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioException("Graduação não encontrada"));
    }

    private Modalidade buscarModalidadePorId(Long id) {
        return modalidadeRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Modalidade não encontrada"));
    }
}
