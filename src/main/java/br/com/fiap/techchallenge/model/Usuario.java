package br.com.fiap.techchallenge.model;

import br.com.fiap.techchallenge.dto.NovoUsuario;
import br.com.fiap.techchallenge.dto.UsuarioEditado;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity(name = "Usuario")
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String nome;

    @Column(name = "cpf", columnDefinition = "bpchar(10)")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String telefone;

    @Column(name = "password_hash", columnDefinition = "text")
    private String senha;

    @Column(name = "user_type")
    private String tipoUsuario;

    @Column(name = "created_at")
    private LocalDateTime criadoEm;

    @Column(name = "updated_at")
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Endereco> enderecos;

    public Usuario() {}

    public Usuario(UUID id, String nome, String cpf, String email, String telefone, String senha, String tipoUsuario, LocalDateTime criadoEm, LocalDateTime atualizadoEm, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.enderecos = enderecos;
    }

    public Usuario(NovoUsuario novoUsuario) {
        setNome(novoUsuario.nome());
        setCpf(novoUsuario.cpf());
        setEmail(novoUsuario.email());
        setTelefone(novoUsuario.telefone());
        setSenha(novoUsuario.senha());
        setTipoUsuario(novoUsuario.tipoUsuario());
        setEnderecos(new LinkedList<>());
        setCriadoEm(LocalDateTime.now());
        setAtualizadoEm(LocalDateTime.now());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void atualizarInformacoes(UsuarioEditado usuarioEditado) {
        setNome(usuarioEditado.nome());
        setCpf(usuarioEditado.cpf());
        setTelefone(usuarioEditado.telefone());
        setEmail(usuarioEditado.email());
        setTipoUsuario(usuarioEditado.tipoUsuario());
        setAtualizadoEm(LocalDateTime.now());
    }
}
