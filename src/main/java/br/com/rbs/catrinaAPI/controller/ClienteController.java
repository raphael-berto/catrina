package br.com.rbs.catrinaAPI.controller;

import br.com.rbs.catrinaAPI.dto.ClienteDTO;
import br.com.rbs.catrinaAPI.model.Cliente;
import br.com.rbs.catrinaAPI.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO cadastrarCliente(@Valid  @RequestBody Cliente cliente){
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<ClienteDTO> consultarPorCpf(@RequestParam String cpf){
        ClienteDTO clienteDTO = clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.ok().body(clienteDTO);
    }

}
