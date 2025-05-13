package br.com.fiap.techchallenge.dto;

import java.util.List;

public record UsuarioEditado(
        String nome,
        String cpf,
        String email,
        String telefone,
        String tipoUsuario,
        List<NovoEndereco> enderecos
) {
}
