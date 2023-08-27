package com.example.meireles.banker.infrastructure.mapper;

import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);
}
