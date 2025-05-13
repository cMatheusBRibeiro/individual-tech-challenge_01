package br.com.fiap.techchallenge.dto;

import br.com.fiap.techchallenge.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UsuarioDetalhamento(
        UUID id,
        String nome,
        String cpf,
        String email,
        String telefone,
        String tipoUsuario,
        List<EnderecoDetalhamento> enderecos,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {

    public UsuarioDetalhamento (Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getTipoUsuario(),
                usuario.getEnderecos().stream().map(endereco -> new EnderecoDetalhamento(
                        endereco.getId(),
                        endereco.getCep(),
                        endereco.getEstado(),
                        endereco.getCidade(),
                        endereco.getBairro(),
                        endereco.getLogradouro(),
                        endereco.getNumero(),
                        endereco.getComplemento()
                )).toList(),
                usuario.getCriadoEm(),
                usuario.getAtualizadoEm()
        );
    }

}
