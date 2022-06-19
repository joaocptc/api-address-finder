package com.buscador.cep.application.impl;

import com.buscador.cep.application.FindAddressUseCase;
import com.buscador.cep.application.mapper.AddressMapper;
import com.buscador.cep.core.models.Address;
import com.buscador.cep.external.FindAddressGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAddressUseCaseImpl implements FindAddressUseCase {

    private final FindAddressGateway findAddressGateway;

    @Override
    public Address execute(final String cep) {
        var dto = findAddressGateway.find(cep);

        return AddressMapper.INSTANCE.toAddress(dto);
    }

}
