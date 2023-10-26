package MyProject.rest.controller;

import MyProject.exception.BusinessRulesException;
import MyProject.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)                                                 //ExceptionHandler mostra q a classe foi identificada como um tratador de erros
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRulesBusinessException(BusinessRulesException exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrors(errorMessage);
    }
}
