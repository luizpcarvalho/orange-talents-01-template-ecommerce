package br.com.zup.mercadolivre.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrosDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrosDeValidacaoResponse>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getBindingResult().getFieldErrors();
        List<ErrosDeValidacaoResponse> errosResponse = new ArrayList<>();

        erros.forEach(erro -> {
            String menssagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            errosResponse.add(new ErrosDeValidacaoResponse(erro.getField(), menssagem));
        });

        return ResponseEntity.badRequest().body(errosResponse);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ErrosDeValidacaoResponse>> bindExceptionHandler(BindException e){
        List<FieldError> erros = e.getBindingResult().getFieldErrors();
        List<ErrosDeValidacaoResponse> errosResponse = new ArrayList<>();

        erros.forEach(erro -> {
            String menssagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            errosResponse.add(new ErrosDeValidacaoResponse(erro.getField(), menssagem));
        });

        return ResponseEntity.badRequest().body(errosResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public  ResponseEntity<ErrosDeValidacaoResponse> responseEntityException(ResponseStatusException e) {
        ErrosDeValidacaoResponse erro = new ErrosDeValidacaoResponse("Produto", e.getReason());
        return ResponseEntity.badRequest().body(erro);
    }

}
