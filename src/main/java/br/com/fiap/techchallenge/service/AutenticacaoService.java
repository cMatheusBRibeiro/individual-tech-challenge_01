package br.com.fiap.techchallenge.service;

import br.com.fiap.techchallenge.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Boolean autenticarUsuario(String email, String senha) {
        var usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario != null) {
            return true;
        }

        throw new RuntimeException("E-mail e/ou senha incorretos!");
    }

}
