package br.com.rbs.catrinaAPI.services;

import br.com.rbs.catrinaAPI.model.Endereco;

public interface EnderecoService {
    Endereco buscarEndereco(Endereco endereco);
    Endereco gravarEndereco(Endereco endereco);
}
