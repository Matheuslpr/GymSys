package dev.matheus.controller;


import dev.matheus.doc.MatriculaControllerDoc;
import dev.matheus.dto.MatriculaRequest;
import dev.matheus.dto.MatriculaResponse;
import dev.matheus.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController implements MatriculaControllerDoc {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse cadastrar(@RequestBody @Valid MatriculaRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<MatriculaResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public MatriculaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public MatriculaResponse atualizar(@PathVariable Long id, @RequestBody @Valid MatriculaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}