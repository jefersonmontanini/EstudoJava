package MyProject.rest.controller;

import MyProject.exception.BusinessRulesException;
import MyProject.exception.OrderNotFoundException;
import MyProject.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRulesException.class)                                                 //ExceptionHandler mostra q a classe foi identificada como um tratador de erros
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRulesBusinessException(BusinessRulesException exception) {
        String errorMessage = exception.getMessage();
        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(objectError ->
                        objectError.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
