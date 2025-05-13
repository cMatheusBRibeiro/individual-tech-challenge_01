package br.com.fiap.techchallenge.dto;

import java.util.List;

public record NovoUsuario(
        String nome,
        String cpf,
        String email,
        String telefone,
        String tipoUsuario,
        String senha,
        List<NovoEndereco> enderecos
) {
}
