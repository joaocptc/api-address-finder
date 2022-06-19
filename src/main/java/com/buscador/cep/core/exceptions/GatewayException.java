package com.buscador.cep.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GatewayException extends RuntimeException {

    private static final long serialVersionUID = 5688770001133945719L;

    public GatewayException(String message) {
        super(message);
    }
}
