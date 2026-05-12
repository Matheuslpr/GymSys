package dev.matheus.controller;

import dev.matheus.dto.AlunoRequest;
import dev.matheus.dto.AlunoResponse;
import dev.matheus.service.AlunoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponse cadastrar(@RequestBody AlunoRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<AlunoResponse> listar(Pageable pageable){
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public AlunoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlunoResponse atualizar(@PathVariable Long id, @RequestBody AlunoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
