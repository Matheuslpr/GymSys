package dev.matheus.controller;

import dev.matheus.dto.GraduacaoRequest;
import dev.matheus.dto.GraduacaoResponse;
import dev.matheus.service.GraduacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graduacoes")
public class GraduacaoController {

    private final GraduacaoService service;

    public GraduacaoController(GraduacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GraduacaoResponse cadastrar(@RequestBody @Valid GraduacaoRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<GraduacaoResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public GraduacaoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public GraduacaoResponse atualizar(@PathVariable Long id, @RequestBody @Valid GraduacaoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}