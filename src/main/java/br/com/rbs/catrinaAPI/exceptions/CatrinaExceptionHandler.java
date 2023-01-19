package br.com.rbs.catrinaAPI.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice //escuta os erros
public class CatrinaExceptionHandler extends ResponseEntityExceptionHandler {
@Autowired
private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(NotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Erro erro = new Erro();
        erro.setStatus(status.value());
        erro.setTitulo(ex.getMessage());
        erro.setDataHora(LocalDateTime.now());
        return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Erro erro = new Erro();
        erro.setStatus(status.value());
        erro.setTitulo(ex.getMessage());
        erro.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro.Campo> campos = new ArrayList();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {

            String nome = ((FieldError) error).getField();

            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Erro.Campo(nome, mensagem));
        }
        Erro erro = new Erro(status.value(),
                LocalDateTime.now(),
                "Um ou mais campos estão inválidos.");
        erro.setCampos(campos);
        return super.handleExceptionInternal(ex, erro, headers, status, request);
    }

}
