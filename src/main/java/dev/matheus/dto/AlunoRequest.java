package dev.matheus.dto;

import dev.matheus.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public record AlunoRequest(
        String nome,
        LocalDate dataNascimento,
        String sexo,
        String telefone,
        String celular,
        String email,
        String observacoes,
        String endereco,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
        ){

    public Aluno toEntity(){
        Aluno aluno = new Aluno();
        preencher(aluno);
        return aluno;
    }

    public void preencher(Aluno aluno){
        aluno.setNome(nome);
        aluno.setDataNascimento(dataNascimento);
        aluno.setSexo(sexo);
        aluno.setNumero(numero);
        aluno.setTelefone(telefone);
        aluno.setCelular(celular);
        aluno.setEmail(email);
        aluno.setObservacoes(observacoes);
        aluno.setEndereco(endereco);
        aluno.setNumero(numero);
        aluno.setComplemento(complemento);
        aluno.setBairro(bairro);
        aluno.setCidade(cidade);
        aluno.setEstado(estado);
        aluno.setCep(cep);
    }
}
