package br.com.rbs.catrinaAPI.serializer;

import br.com.rbs.catrinaAPI.model.Cliente;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ClienteSerializer implements Serializer<Cliente>{

    @Override
    public byte[] serialize(String s, Cliente cliente) {
        try {
            return new ObjectMapper().writeValueAsBytes(cliente);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
