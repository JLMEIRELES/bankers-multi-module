package com.meireles.example.infrastructure.mapper;

import com.meireles.example.infrastructure.entity.CustomerEntity;
import com.meireles.example.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);
}
