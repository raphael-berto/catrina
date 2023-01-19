package br.com.rbs.catrinaAPI.builders;

import br.com.rbs.catrinaAPI.model.Cliente;
import br.com.rbs.catrinaAPI.model.Endereco;

import java.math.BigInteger;
import java.time.LocalDate;

public class Builders {


    public static Cliente getClienteNow(){
        return new Cliente(Long.valueOf(1), "Joel teste", "35897868421", "+5519987564423",
                LocalDate.now(), getEnderecoNow());
    }

    public static Endereco getEnderecoNow(){
        return new Endereco("18664588", BigInteger.valueOf(165),"Rua das cabras",
                "Ao lado 2", "Centro", "Sorocaba", "SP");
    }

}
