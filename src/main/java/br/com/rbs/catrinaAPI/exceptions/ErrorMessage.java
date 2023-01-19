package br.com.rbs.catrinaAPI.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private LocalDateTime dataAtual;
    private String mensagemErro;

    public ErrorMessage(LocalDateTime dataAtual, String mensagemErro) {
        this.dataAtual = dataAtual;
        this.mensagemErro = mensagemErro;
    }
}
