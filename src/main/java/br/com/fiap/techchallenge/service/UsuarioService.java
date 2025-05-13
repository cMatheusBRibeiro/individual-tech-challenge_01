package br.com.fiap.techchallenge.service;

import br.com.fiap.techchallenge.dto.NovoUsuario;
import br.com.fiap.techchallenge.dto.UsuarioEditado;
import br.com.fiap.techchallenge.model.Endereco;
import br.com.fiap.techchallenge.model.Usuario;
import br.com.fiap.techchallenge.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoService enderecoService;

    public UsuarioService(UsuarioRepository usuarioRepository, EnderecoService enderecoService) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoService = enderecoService;
    }

    public Usuario buscarUsuarioPorId (UUID id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public Page<Usuario> buscarUsuariosPaginado (Pageable paginacao) {
        return usuarioRepository.findAll(paginacao);
    }

    public List<Usuario> buscarTodosOsUsuarios () {
        return usuarioRepository.findAll();
    }

    public Usuario adicionarNovoUsuario (NovoUsuario novoUsuario) {
        var usuario = new Usuario(novoUsuario);

        usuario = usuarioRepository.save(usuario);

        return usuario;
    }

    public Usuario atualizarUsuario (UUID id, UsuarioEditado usuarioEditado) {
        var usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        var usuario = usuarioOptional.get();

        usuario.atualizarInformacoes(usuarioEditado);

        usuarioRepository.save(usuario);

        return usuario;
    }

    public void removerUsuario (UUID id) {
        var usuarioOp = usuarioRepository.findById(id);

        if (usuarioOp.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        var usuario = usuarioOp.get();

        usuario.getEnderecos().forEach(endereco -> enderecoService.removerEndereco(endereco.getId()));

        usuarioRepository.deleteById(id);
    }

}
