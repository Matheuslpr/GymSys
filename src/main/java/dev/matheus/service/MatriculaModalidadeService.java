package dev.matheus.service;

import dev.matheus.dto.HistoricoGraduacaoResponse;
import dev.matheus.dto.MatriculaModalidadeRequest;
import dev.matheus.dto.MatriculaModalidadeResponse;
import dev.matheus.exception.RegraNegocioException;
import dev.matheus.model.*;
import dev.matheus.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MatriculaModalidadeService {

    private final MatriculaModalidadeRepository repository;
    private final MatriculaRepository matriculaRepository;
    private final ModalidadeRepository modalidadeRepository;
    private final GraduacaoRepository graduacaoRepository;
    private final PlanoRepository planoRepository;
    private final HistoricoGraduacaoRepository historicoGraduacaoRepository;

    public MatriculaModalidadeService(MatriculaModalidadeRepository repository,
                                      MatriculaRepository matriculaRepository,
                                      ModalidadeRepository modalidadeRepository,
                                      GraduacaoRepository graduacaoRepository,
                                      PlanoRepository planoRepository,
                                      HistoricoGraduacaoRepository historicoGraduacaoRepository) {
        this.repository = repository;
        this.matriculaRepository = matriculaRepository;
        this.modalidadeRepository = modalidadeRepository;
        this.graduacaoRepository = graduacaoRepository;
        this.planoRepository = planoRepository;
        this.historicoGraduacaoRepository = historicoGraduacaoRepository;
    }

    public MatriculaModalidadeResponse cadastrar(MatriculaModalidadeRequest request) {
        if (repository.existsByMatriculaIdAndModalidadeId(request.matriculaId(), request.modalidadeId())) {
            throw new RegraNegocioException("Aluno já matriculado nessa modalidade");
        }
        MatriculaModalidade matriculaModalidade = request.toEntity();
        preencherRelacionamentos(matriculaModalidade, request);
        MatriculaModalidade salvo = repository.save(matriculaModalidade);
        registrarHistoricoGraduacao(salvo);
        return MatriculaModalidadeResponse.fromEntity(salvo);
    }

    public Page<MatriculaModalidadeResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(MatriculaModalidadeResponse::fromEntity);
    }

    public MatriculaModalidadeResponse buscarPorId(Long id) {
        MatriculaModalidade matriculaModalidade = buscarEntidadePorId(id);
        return MatriculaModalidadeResponse.fromEntity(matriculaModalidade);
    }

    public MatriculaModalidadeResponse atualizar(Long id, MatriculaModalidadeRequest request) {
        MatriculaModalidade matriculaModalidade = buscarEntidadePorId(id);

        if (repository.existsByMatriculaIdAndModalidadeIdAndIdNot(request.matriculaId(), request.modalidadeId(), id)) {
            throw new RegraNegocioException("Aluno já matriculado nessa modalidade");
        }

        Long graduacaoAnteriorId = matriculaModalidade.getGraduacao() != null
                ? matriculaModalidade.getGraduacao().getId()
                : null;

        request.preencher(matriculaModalidade);
        preencherRelacionamentos(matriculaModalidade, request);
        MatriculaModalidade atualizado = repository.save(matriculaModalidade);

        if (!Objects.equals(graduacaoAnteriorId, request.graduacaoId())) {
            registrarHistoricoGraduacao(atualizado);
        }

        return MatriculaModalidadeResponse.fromEntity(atualizado);
    }

    public void excluir(Long id) {
        MatriculaModalidade matriculaModalidade = buscarEntidadePorId(id);
        repository.delete(matriculaModalidade);
    }

    public List<HistoricoGraduacaoResponse> listarHistoricoGraduacoes(Long matriculaModalidadeId) {
        buscarEntidadePorId(matriculaModalidadeId);
        return historicoGraduacaoRepository
                .findByMatriculaModalidadeIdOrderByDataAlteracaoDesc(matriculaModalidadeId)
                .stream()
                .map(HistoricoGraduacaoResponse::fromEntity)
                .toList();
    }

    private void registrarHistoricoGraduacao(MatriculaModalidade matriculaModalidade) {
        HistoricoGraduacao historico = new HistoricoGraduacao();
        historico.setMatriculaModalidade(matriculaModalidade);
        historico.setGraduacao(matriculaModalidade.getGraduacao());
        historicoGraduacaoRepository.save(historico);
    }

    private void preencherRelacionamentos(MatriculaModalidade matriculaModalidade, MatriculaModalidadeRequest request) {
        matriculaModalidade.setMatricula(buscarMatriculaPorId(request.matriculaId()));
        matriculaModalidade.setModalidade(buscarModalidadePorId(request.modalidadeId()));
        matriculaModalidade.setPlano(buscarPlanoPorId(request.planoId()));
        matriculaModalidade.setGraduacao(buscarGraduacaoPorId(request.graduacaoId()));
    }

    private MatriculaModalidade buscarEntidadePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraNegocioException("Matrícula/Modalidade não encontrada"));
    }

    private Matricula buscarMatriculaPorId(Long id) {
        return matriculaRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Matrícula não encontrada"));
    }

    private Modalidade buscarModalidadePorId(Long id) {
        return modalidadeRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Modalidade não encontrada"));
    }

    private Graduacao buscarGraduacaoPorId(Long id) {
        return graduacaoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Graduação não encontrada"));
    }

    private Plano buscarPlanoPorId(Long id) {
        return planoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Plano não encontrado"));
    }
}