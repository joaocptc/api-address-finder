package com.buscador.cep.application;

import com.buscador.cep.core.models.Address;

public interface FindAddressUseCase {

    Address execute(String cep);
}
