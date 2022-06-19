package com.buscador.cep.entrypoint.findaddress;

import com.buscador.cep.application.FindAddressUseCase;
import com.buscador.cep.core.models.Address;
import com.buscador.cep.core.models.ErrorsResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(
        info = @Info(
                title = "API Address Finder",
                description = "API desenvolvida para realizar a consulta de endereços por CEP",
                version = "1.000",
                contact = @Contact(name = "@joãocarlos", email = "jcarlosptc@live.com")
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Servidor Local",
                variables = {
                        @ServerVariable(name = "serverUrl", defaultValue = "localhost"),
                        @ServerVariable(name = "serverHttpPort", defaultValue = "8080")
                }))
@RestController
@RequestMapping("/address-finder")
@Slf4j
@RequiredArgsConstructor
public class FindAddressController {

    private final FindAddressUseCase findAddressUseCase;

    @Operation(summary = "Consulta endereço completo por CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found address",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "not found",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class)) })
    })
    @GetMapping("/{cep}")
    public ResponseEntity<Address> find(
            @Parameter(description = "CEP do endereço desejado!") @PathVariable(name = "cep") final String cep) {

        var address = findAddressUseCase.execute(cep);

        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
