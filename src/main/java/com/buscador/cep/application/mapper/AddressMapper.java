package com.buscador.cep.application.mapper;

import com.buscador.cep.core.dtos.AddressDTO;
import com.buscador.cep.core.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    Address toAddress(AddressDTO dto);
}
