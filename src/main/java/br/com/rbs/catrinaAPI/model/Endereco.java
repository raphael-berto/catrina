package br.com.rbs.catrinaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@NoArgsConstructor
@Data
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private BigInteger numero;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private String complemento;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String uf;

    public Endereco(String cep, BigInteger numero, String logradouro, String complemento, String bairro, String cidade, String uf) {
        this.cep = cep;
        this.numero = numero;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
}
