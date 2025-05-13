package br.com.fiap.techchallenge.dto;

public record NovoEndereco(
        String cep,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        Integer numero,
        String complemento
) {
}
