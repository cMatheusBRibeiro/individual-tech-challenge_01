package br.com.fiap.techchallenge.repository;

import br.com.fiap.techchallenge.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    public List<Endereco> findAllByUsuarioId (UUID userId);

}
