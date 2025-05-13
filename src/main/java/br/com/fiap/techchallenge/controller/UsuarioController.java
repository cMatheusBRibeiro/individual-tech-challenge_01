package br.com.fiap.techchallenge.controller;

import br.com.fiap.techchallenge.dto.NovoUsuario;
import br.com.fiap.techchallenge.dto.UsuarioDetalhamento;
import br.com.fiap.techchallenge.dto.UsuarioEditado;
import br.com.fiap.techchallenge.service.EnderecoService;
import br.com.fiap.techchallenge.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EnderecoService enderecoService;

    public UsuarioController(UsuarioService usuarioService, EnderecoService enderecoService) {
        this.usuarioService = usuarioService;
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDetalhamento>> buscarTodosOsUsuariosPaginado(
            @PageableDefault(size = 10, page = 0, sort = {"nome"})
            Pageable paginacao
    ) {
        try {
            var usuarios = usuarioService.buscarUsuariosPaginado(paginacao);

            return ResponseEntity.ok().body(usuarios.map(UsuarioDetalhamento::new));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioDetalhamento>> buscarTodosOsUsuarios() {
        try {
            var usuarios = usuarioService.buscarTodosOsUsuarios();

            return ResponseEntity.ok().body(usuarios.stream().map(UsuarioDetalhamento::new).toList());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioDetalhamento> adicionarUsuario(
            @RequestBody NovoUsuario novoUsuario,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        try {
            var usuarioCadastrado = usuarioService.adicionarNovoUsuario(novoUsuario);

            usuarioCadastrado.setEnderecos(
                    novoUsuario
                            .enderecos()
                            .stream()
                            .map(endereco -> enderecoService.adicionarNovoEndereco(usuarioCadastrado, endereco))
                            .toList()
            );

            var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioCadastrado.getId()).toUri();

            return ResponseEntity.created(uri).body(new UsuarioDetalhamento(usuarioCadastrado));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDetalhamento> atualizarUsuario(
            @PathVariable UUID id,
            @RequestBody UsuarioEditado usuarioEditado
    ) {
        try {
            var usuario = usuarioService.atualizarUsuario(id, usuarioEditado);

            usuario.setEnderecos(enderecoService.atualizarEnderecosDoUsuario(usuario, usuarioEditado.enderecos()));

            return ResponseEntity.ok().body(new UsuarioDetalhamento(usuario));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDetalhamento> removerUsuario(
            @PathVariable UUID id
    ) {
        try {
            usuarioService.removerUsuario(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
