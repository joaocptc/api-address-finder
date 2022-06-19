package com.buscador.cep.entrypoint.handler;

import com.buscador.cep.core.exceptions.GatewayException;
import com.buscador.cep.core.models.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = GatewayException.class)
    protected ResponseEntity<ErrorsResponse> handleInternalServerError(RuntimeException ex) {

        var errorData = ErrorsResponse
                .builder()
                .title("INTERNAL SERVER ERROR")
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return new ResponseEntity<>(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
