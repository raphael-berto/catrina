package br.com.rbs.catrinaAPI.services.impl;

import br.com.rbs.catrinaAPI.builders.Builders;
import br.com.rbs.catrinaAPI.dto.ClienteDTO;
import br.com.rbs.catrinaAPI.exceptions.NotFoundException;
import br.com.rbs.catrinaAPI.model.Cliente;
import br.com.rbs.catrinaAPI.model.Endereco;
import br.com.rbs.catrinaAPI.repository.ClienteRepository;
import br.com.rbs.catrinaAPI.services.ClienteService;
import br.com.rbs.catrinaAPI.services.EnderecoService;
import br.com.rbs.catrinaAPI.services.PublicacaoKafkaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class ClienteServiceImplTest {
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private EnderecoService enderecoService;
    @Mock
    private PublicacaoKafkaService publicacaoKafkaService;

    private ClienteService clienteService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        clienteService = new ClienteServiceImpl(clienteRepository, enderecoService, publicacaoKafkaService);
    }

    @Test
    public void validarCadastroCliente(){
        Cliente cliente = Builders.getClienteNow();
        Endereco endereco = Builders.getEnderecoNow();

        when(enderecoService.buscarEndereco(endereco)).thenReturn(endereco);
        when(enderecoService.gravarEndereco(endereco)).thenReturn(endereco);
        when(clienteRepository.save(anyObject())).thenReturn(cliente);

        ClienteDTO clienteDTO = clienteService.cadastrarCliente(cliente);

        Assertions.assertNotNull(clienteDTO);
    }

    @Test
    public void validarBuscarClientePorCpf(){
        Cliente cliente = Builders.getClienteNow();
        Optional<Cliente> clienteOpt = Optional.of(cliente);

        when(clienteRepository.findBycpf(anyObject())).thenReturn(clienteOpt);

        ClienteDTO clienteDTO = clienteService.buscarClientePorCpf(cliente.getCpf());

        Assertions.assertNotNull(clienteDTO);
    }

    @Test
    public void validarBuscarClientePorCpf_clienteNaoEncontrado_falha(){
        Cliente cliente = Builders.getClienteNow();
        Optional<Cliente> clienteOpt = Optional.empty();

        when(clienteRepository.findBycpf(anyObject())).thenReturn(clienteOpt);

        Assertions.assertThrows(NotFoundException.class, () -> {
            clienteService.buscarClientePorCpf(cliente.getCpf());
        });
    }
}
