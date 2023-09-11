package com.example.meireles.banker.infrastructure.mapper;

import com.example.meireles.banker.domain.model.Address;
import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.infrastructure.client.dto.Endereco;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);

    @Mapping(source = "bairro", target = "neighborhood")
    @Mapping(source = "cep", target = "zipCode")
    @Mapping(source = "complemento", target = "complement")
    @Mapping(source = "localidade", target = "city")
    @Mapping(source = "logradouro", target = "street")
    @Mapping(source = "uf", target = "state")
    Address toAddress(Endereco endereco);
}
