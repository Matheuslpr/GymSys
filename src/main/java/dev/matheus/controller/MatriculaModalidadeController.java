package dev.matheus.controller;

import dev.matheus.doc.MatriculaModalidadeControllerDoc;
import dev.matheus.dto.HistoricoGraduacaoResponse;
import dev.matheus.dto.MatriculaModalidadeRequest;
import dev.matheus.dto.MatriculaModalidadeResponse;
import dev.matheus.service.MatriculaModalidadeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas-modalidades")
public class MatriculaModalidadeController implements MatriculaModalidadeControllerDoc {

    private final MatriculaModalidadeService service;

    public MatriculaModalidadeController(MatriculaModalidadeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaModalidadeResponse cadastrar(@RequestBody @Valid MatriculaModalidadeRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<MatriculaModalidadeResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public MatriculaModalidadeResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/{id}/historico-graduacoes")
    public List<HistoricoGraduacaoResponse> listarHistoricoGraduacoes(@PathVariable Long id) {
        return service.listarHistoricoGraduacoes(id);
    }

    @PutMapping("/{id}")
    public MatriculaModalidadeResponse atualizar(@PathVariable Long id, @RequestBody @Valid MatriculaModalidadeRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
