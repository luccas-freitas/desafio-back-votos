package br.com.southsystem.desafiobackvotos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InternalServerErrorAdvice {
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Map<String, String> internalErrorHandler(InternalServerErrorException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("error", "500");
        return response;
    }
}
