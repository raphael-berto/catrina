package br.com.rbs.catrinaAPI.services.impl;

import java.util.Properties;

import br.com.rbs.catrinaAPI.model.Cliente;
import br.com.rbs.catrinaAPI.serializer.ClienteSerializer;
import br.com.rbs.catrinaAPI.services.PublicacaoKafkaService;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PublicacaoKafkaServiceImlp implements PublicacaoKafkaService {

    @Value("${br.com.rbs.catrina.kafka.url}")
    private String urlServidorKafka;
    @Value("${br.com.rbs.catrina.kafka.topico}")
    private String topico;

    @Override
    public Cliente publicarMensagem(Cliente cliente) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, urlServidorKafka);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ClienteSerializer.class.getName());

        try (KafkaProducer<String, Cliente> producer = new KafkaProducer<String, Cliente>(properties)) {
            ProducerRecord<String, Cliente> record = new ProducerRecord<String, Cliente>(topico, cliente);
            producer.send(record);
        }
        return cliente;
    }
}
