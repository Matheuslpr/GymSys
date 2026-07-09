package dev.matheus.controller;

import dev.matheus.dto.AssiduidadeRequest;
import dev.matheus.dto.AssiduidadeResponse;
import dev.matheus.service.AssiduidadeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assiduidades")
public class AssiduidadeController {

    private final AssiduidadeService service;

    public AssiduidadeController(AssiduidadeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssiduidadeResponse cadastrar(@RequestBody @Valid AssiduidadeRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<AssiduidadeResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public AssiduidadeResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AssiduidadeResponse atualizar(@PathVariable Long id, @RequestBody @Valid AssiduidadeRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}