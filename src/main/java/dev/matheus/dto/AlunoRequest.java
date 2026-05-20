package dev.matheus.dto;

import dev.matheus.model.Aluno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public record AlunoRequest(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 150, message = "O nome deve conter no máximo 150 caracteres.")
        String nome,

        @Past(message = "A data de nascimento deve estar no passado.")
        LocalDate dataNascimento,

        @Size(max = 1, message = "O sexo deve conter apenas um caractere (M ou F).")
        String sexo,

        @Size(max = 30, message = "O telefone deve conter no máximo 30 caracteres.")
        String telefone,

        @Size(max = 30, message = "O celular deve conter no máximo 30 caracteres.")
        String celular,

        @Email(message = "O E-mail inválido.")
        @Size(max = 150, message = "O E-mail deve conter no máximo 150 caracteres.")
        String email,

        String observacoes,

        @Size(max = 150, message = "O endereço deve conter no máximo 150 caracteres.")
        String endereco,

        @Size(max = 20, message = "O número deve conter no máximo 20 caracteres.")
        String numero,

        @Size(max = 100, message = "O complemento deve conter no máximo 100 caracteres.")
        String complemento,

        @Size(max = 100, message = "O bairro deve conter no máximo 100 caracteres.")
        String bairro,

        @Size(max = 100, message = "O cidade deve conter no máximo 100 caracteres.")
        String cidade,

        @Size(max = 2, message = "O estado deve conter no máximo 02 caracteres.")
        String estado,

        @Size(max = 20, message = "O CEP deve conter no máximo 20 caracteres.")
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
