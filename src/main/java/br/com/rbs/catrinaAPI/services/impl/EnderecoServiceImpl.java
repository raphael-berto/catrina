package br.com.rbs.catrinaAPI.services.impl;

import br.com.rbs.catrinaAPI.exceptions.NegocioException;
import br.com.rbs.catrinaAPI.model.Endereco;
import br.com.rbs.catrinaAPI.model.ws.viacep.ViaCepRS;
import br.com.rbs.catrinaAPI.repository.EnderecoRepository;
import br.com.rbs.catrinaAPI.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.Collections;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Value("${br.com.rbs.ws.viacep.url}")
    private String urlViaCep;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco buscarEndereco(Endereco endereco) {
        ViaCepRS viaCepRS = buscarEnderecoApiViaCep(endereco.getCep());
        Endereco enderecoEncontrado = montarEnderecoEncontrado(viaCepRS, endereco.getNumero());
        return enderecoEncontrado;
    }

    private Endereco montarEnderecoEncontrado(ViaCepRS viaCepRS, BigInteger numero) {
        return new Endereco(viaCepRS.getCep(), numero, viaCepRS.getLogradouro(),
                viaCepRS.getComplemento(), viaCepRS.getBairro(), viaCepRS.getLocalidade(), viaCepRS.getUf());
    }

    public ViaCepRS buscarEnderecoApiViaCep(String cep){
        String endpointViaCep = urlViaCep + cep + "/json/";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<ViaCepRS> response = restTemplate.exchange(endpointViaCep, HttpMethod.GET, entity, ViaCepRS.class);
        validarErrosDeRetorno(response);
        return response.getBody();
    }

    private void validarErrosDeRetorno(ResponseEntity<ViaCepRS> response) {
        String mensagemDeErro = "Erro ao buscar endereço -> Serviço de consulta de Cep indisponivel";
        if(!HttpStatus.OK.equals(response.getStatusCode())){
            throw new NegocioException(mensagemDeErro);
        }
        if(response.getBody().getErro() != null && response.getBody().getErro().equals("true")){
            throw new NegocioException(mensagemDeErro);
        }
    }

    @Override
    public Endereco gravarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}
