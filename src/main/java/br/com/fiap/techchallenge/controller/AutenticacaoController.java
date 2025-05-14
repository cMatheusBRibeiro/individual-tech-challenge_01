package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.dto.DadosAutenticacao;
import br.com.fiap.techchallenge.service.AutenticacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
    public ResponseEntity<String> autenticacao (@RequestBody DadosAutenticacao dadosAutenticacao) {
        try {
            var autorizado = autenticacaoService.autenticarUsuario(dadosAutenticacao.email(), dadosAutenticacao.senha());

            if (autorizado) {
                return ResponseEntity.ok("Acesso autorizado!");
            }

            return ResponseEntity.badRequest().body("E-mail e/ou senha incorretos!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
