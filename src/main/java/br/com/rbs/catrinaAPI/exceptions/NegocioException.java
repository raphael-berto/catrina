package br.com.rbs.catrinaAPI.exceptions;

public class NegocioException extends RuntimeException{
    public NegocioException(String message) {
        super(message);
    }
}
