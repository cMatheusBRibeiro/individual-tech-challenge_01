package br.com.fiap.techchallenge.dto;

public record DadosTrocaDeSenha(
        String senhaAtual,
        String novaSenha,
        String confirmacaoSenha
) {
}
