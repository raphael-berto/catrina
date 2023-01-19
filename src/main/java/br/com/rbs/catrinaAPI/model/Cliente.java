package br.com.rbs.catrinaAPI.model;

import br.com.rbs.catrinaAPI.exceptions.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Id
    @NotNull(groups = ValidationGroups.Cliente.class) //determino que esta validação só vai acontecer dentro de um determinado grupo
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false)
    private String cpf;

    @NotBlank
    @Size(max = 14)
    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
}
