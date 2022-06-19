package com.buscador.cep.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorsResponse {

    private String title;
    private int errorCode;
    private String errorMessage;

}
