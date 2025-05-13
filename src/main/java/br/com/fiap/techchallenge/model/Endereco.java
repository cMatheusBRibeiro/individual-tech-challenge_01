package br.com.fiap.techchallenge.model;

import br.com.fiap.techchallenge.dto.NovoEndereco;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "Endereco")
@Table(name = "addresses")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @Column(name = "zip_code", columnDefinition = "bpchar(8)")
    private String cep;

    @Column(name = "state", columnDefinition = "bpchar(2)")
    private String estado;

    @Column(name = "city")
    private String cidade;

    @Column(name = "neighborhood")
    private String bairro;

    @Column(name = "street")
    private String logradouro;

    @Column(name = "number")
    private Integer numero;

    @Column(name = "complement")
    private String complemento;

    public Endereco() {}

    public Endereco(
            UUID id,
            Usuario usuario,
            String cep,
            String estado,
            String cidade,
            String bairro,
            String logradouro,
            Integer numero,
            String complemento
    ) {
        this.id = id;
        this.usuario = usuario;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(Usuario usuario, NovoEndereco novoEndereco) {
        setUsuario(usuario);
        setCep(novoEndereco.cep());
        setEstado(novoEndereco.estado());
        setCidade(novoEndereco.cidade());
        setBairro(novoEndereco.bairro());
        setLogradouro(novoEndereco.logradouro());
        setNumero(novoEndereco.numero());
        setComplemento(novoEndereco.complemento());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
