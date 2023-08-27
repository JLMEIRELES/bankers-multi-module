package com.example.meireles.banker.application.mapper;

import com.example.meireles.banker.application.dto.request.CustomerRequest;
import com.example.meireles.banker.application.dto.response.CustomerResponse;
import com.example.meireles.banker.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    Customer toCustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer addedCustomer);
}
