package com.meireles.example.application.mapper;

import com.meireles.example.application.dto.request.CustomerRequest;
import com.meireles.example.application.dto.response.CustomerResponse;
import com.meireles.example.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer addedCustomer);
}
