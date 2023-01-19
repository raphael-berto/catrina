package br.com.rbs.catrinaAPI.controller;

import br.com.rbs.catrinaAPI.builders.Builders;
import br.com.rbs.catrinaAPI.dto.ClienteDTO;
import br.com.rbs.catrinaAPI.model.Cliente;
import br.com.rbs.catrinaAPI.services.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    private ClienteController clienteController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        clienteController = new ClienteController(clienteService);
    }

    @Test
    public void validarCadastroCliente(){
        Cliente cliente = Builders.getClienteNow();

        when(clienteService.cadastrarCliente(anyObject())).thenReturn(new ClienteDTO(cliente));

        ClienteDTO clienteDTO = clienteController.cadastrarCliente(cliente);

        Assertions.assertNotNull(clienteDTO);
    }

    @Test
    public void validarBuscarClientePorCpf(){
        Cliente cliente = Builders.getClienteNow();

        when(clienteService.buscarClientePorCpf(anyObject())).thenReturn(new ClienteDTO(cliente));

        ResponseEntity responseEntity = clienteController.consultarPorCpf(cliente.getCpf());

        Assertions.assertNotNull(responseEntity);
    }

    @Test
    public void validarBuscarClientePorCpf_200(){
        Cliente cliente = Builders.getClienteNow();

        when(clienteService.buscarClientePorCpf(anyObject())).thenReturn(new ClienteDTO(cliente));

        ResponseEntity responseEntity = clienteController.consultarPorCpf(cliente.getCpf());

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
