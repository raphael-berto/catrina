package br.com.rbs.catrinaAPI.services;

import br.com.rbs.catrinaAPI.dto.ClienteDTO;
import br.com.rbs.catrinaAPI.model.Cliente;

import java.util.List;

public interface ClienteService {
    ClienteDTO cadastrarCliente(Cliente cliente);

    ClienteDTO buscarClientePorCpf(String cpf);
}
