package br.com.fiap.techchallenge.service;

import br.com.fiap.techchallenge.dto.NovoEndereco;
import br.com.fiap.techchallenge.model.Endereco;
import br.com.fiap.techchallenge.model.Usuario;
import br.com.fiap.techchallenge.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco adicionarNovoEndereco (Usuario usuario, NovoEndereco novoEndereco) {
        var endereco = new Endereco(usuario, novoEndereco);

        enderecoRepository.save(endereco);

        return endereco;
    }

    public List<Endereco> atualizarEnderecosDoUsuario (Usuario usuario, List<NovoEndereco> novosEnderecos) {
        var enderecosExistentes = enderecoRepository.findAllByUsuarioId(usuario.getId());

        enderecosExistentes.forEach(endereco -> removerEndereco(endereco.getId()));

        return novosEnderecos
                .stream()
                .map(novoEndereco -> enderecoRepository.save(new Endereco(usuario, novoEndereco)))
                .toList();
    }

    public void removerEndereco (UUID idEndereco) {
        if (!enderecoRepository.existsById(idEndereco)) {
            throw new RuntimeException("Endereço não existe");
        }

        enderecoRepository.deleteById(idEndereco);
    }

}
