package com.buscador.cep.external;

import com.buscador.cep.core.dtos.AddressDTO;

public interface FindAddressGateway {

    AddressDTO find(String cep);
}
