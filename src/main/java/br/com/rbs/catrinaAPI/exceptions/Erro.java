package br.com.rbs.catrinaAPI.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Erro {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

    public Erro() {
        super();
    }

    public Erro(Integer status, LocalDateTime dataHora, String titulo) {
        super();
        this.status = status;
        this.dataHora = dataHora;
        this.titulo = titulo;
    }

    @Setter
    @Getter
    public static class Campo{
        private String nome;
        private String mensagem;

        public Campo(String nome, String mensagem) {
            super();
            this.nome = nome;
            this.mensagem = mensagem;
        }
    }
}
