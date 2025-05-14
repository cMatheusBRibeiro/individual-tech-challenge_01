package br.com.fiap.techchallenge.repository;

import br.com.fiap.techchallenge.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    List<Usuario> findAll();

    Page<Usuario> findAll(Pageable paginacao);

    Usuario findByEmailAndSenha(String email, String senha);

}
