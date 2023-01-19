package br.com.rbs.catrinaAPI.dto;

import br.com.rbs.catrinaAPI.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.math.BigInteger;

@Data
public class EnderecoDTO {
    private String cep;
    private BigInteger numero;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    public EnderecoDTO(Cliente cliente) {
        this.cep = cliente.getEndereco().getCep();
        this.numero = cliente.getEndereco().getNumero();
        this.logradouro = cliente.getEndereco().getLogradouro();
        this.complemento = cliente.getEndereco().getComplemento();
        this.bairro = cliente.getEndereco().getBairro();
        this.cidade = cliente.getEndereco().getCidade();
        this.uf = cliente.getEndereco().getUf();
    }
}
