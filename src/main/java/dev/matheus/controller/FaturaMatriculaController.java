package dev.matheus.controller;

import dev.matheus.dto.FaturaMatriculaRequest;
import dev.matheus.dto.FaturaMatriculaResponse;
import dev.matheus.service.FaturaMatriculaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faturas-matriculas")
public class FaturaMatriculaController {

    private final FaturaMatriculaService service;

    public FaturaMatriculaController(FaturaMatriculaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FaturaMatriculaResponse cadastrar(@RequestBody @Valid FaturaMatriculaRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<FaturaMatriculaResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public FaturaMatriculaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public FaturaMatriculaResponse atualizar(@PathVariable Long id, @RequestBody @Valid FaturaMatriculaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
