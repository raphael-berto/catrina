package br.com.rbs.catrinaAPI.services;

import br.com.rbs.catrinaAPI.model.Cliente;

public interface PublicacaoKafkaService {
    Cliente publicarMensagem(Cliente cliente);
}
