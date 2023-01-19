package br.com.rbs.catrinaAPI.dto;

import br.com.rbs.catrinaAPI.model.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String telefone;
    private String dataDeNascimento;
    @JsonProperty("endereco")
    private EnderecoDTO enderecoDTO;

    public ClienteDTO(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataDeNascimento = cliente.getDataDeNascimento().toString();
        this.enderecoDTO = new EnderecoDTO(cliente);
    }
}
