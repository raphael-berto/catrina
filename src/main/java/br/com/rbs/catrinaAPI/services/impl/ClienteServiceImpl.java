package br.com.rbs.catrinaAPI.services.impl;

import br.com.rbs.catrinaAPI.dto.ClienteDTO;
import br.com.rbs.catrinaAPI.exceptions.NotFoundException;
import br.com.rbs.catrinaAPI.model.Cliente;
import br.com.rbs.catrinaAPI.model.Endereco;
import br.com.rbs.catrinaAPI.repository.ClienteRepository;
import br.com.rbs.catrinaAPI.repository.EnderecoRepository;
import br.com.rbs.catrinaAPI.services.ClienteService;
import br.com.rbs.catrinaAPI.services.EnderecoService;
import br.com.rbs.catrinaAPI.services.PublicacaoKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    private EnderecoService enderecoService;

    private PublicacaoKafkaService publicacaoKafkaService;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoService enderecoService, PublicacaoKafkaService publicacaoKafkaService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
        this.publicacaoKafkaService = publicacaoKafkaService;
    }

    @Override
    public ClienteDTO cadastrarCliente(Cliente cliente){

        //Buscar endereço via cep
        Endereco enderecoConsultado = enderecoService.buscarEndereco(cliente.getEndereco());

        //cadastrar o endereco
        Endereco endereco = enderecoService.gravarEndereco(enderecoConsultado);

        //Adiciona o endereco ao cliente
        cliente.setEndereco(endereco);

        //salvar no banco
        Cliente clienteSalvo = clienteRepository.save(cliente);

        //publicar topico kafka
        publicacaoKafkaService.publicarMensagem(clienteSalvo);

        return new ClienteDTO(clienteSalvo);
    }

    @Override
    public ClienteDTO buscarClientePorCpf(String cpf) {
        Optional<Cliente> cliente = clienteRepository.findBycpf(cpf);
        return new ClienteDTO(cliente.orElseThrow(() -> new NotFoundException("Cliente não encontrado")));
    }
}
