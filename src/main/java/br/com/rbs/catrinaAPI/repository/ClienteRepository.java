package br.com.rbs.catrinaAPI.repository;

import br.com.rbs.catrinaAPI.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findBycpf(String cpf);
}
