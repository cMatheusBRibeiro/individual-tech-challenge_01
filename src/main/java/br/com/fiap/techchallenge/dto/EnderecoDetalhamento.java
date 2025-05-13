package br.com.fiap.techchallenge.dto;

import java.util.UUID;

public record EnderecoDetalhamento(
        UUID id,
        String cep,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        Integer numero,
        String complemento
) {
}
