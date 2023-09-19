package com.example.meireles.banker.infrastructure.mapper;

import com.example.meireles.banker.domain.model.Address;
import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.infrastructure.client.dto.Endereco;
import com.example.meireles.banker.infrastructure.entity.AddressEntity;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "userPassword", source = "password")
    CustomerEntity toCustomerEntity(Customer customer);

    @Mapping(target = "password", source = "userPassword")
    Customer toCustomer(CustomerEntity customerEntity);

    @Mapping(source = "bairro", target = "neighborhood")
    @Mapping(source = "cep", target = "zipCode")
    @Mapping(source = "complemento", target = "complement")
    @Mapping(source = "localidade", target = "city")
    @Mapping(source = "logradouro", target = "street")
    @Mapping(source = "uf", target = "state")
    Address toAddress(Endereco endereco);

    Address toAddress(AddressEntity addressEntity);

    default Address mergeAddress(Address targetAddress, Address sourceAddress) {
        return Address.builder().
                zipCode(targetAddress.getZipCode() != null ? targetAddress.getZipCode() : sourceAddress.getZipCode()).
                state(targetAddress.getState() != null ? targetAddress.getState() : sourceAddress.getState()).
                city(targetAddress.getCity() != null ? targetAddress.getCity() : sourceAddress.getCity()).
                neighborhood(targetAddress.getNeighborhood() != null ? targetAddress.getNeighborhood() : sourceAddress.getNeighborhood()).
                complement(targetAddress.getComplement() != null ? targetAddress.getComplement() : sourceAddress.getComplement()).
                street(targetAddress.getStreet() != null ? targetAddress.getStreet() : sourceAddress.getStreet()).
                id(targetAddress.getId() != null ? targetAddress.getId() : sourceAddress.getId()).
                build();
    }
}
