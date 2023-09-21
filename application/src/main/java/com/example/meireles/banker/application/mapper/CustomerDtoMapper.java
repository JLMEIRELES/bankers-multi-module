package com.example.meireles.banker.application.mapper;

import com.example.meireles.banker.application.dto.request.CustomerRequest;
import com.example.meireles.banker.application.dto.response.CustomerResponse;
import com.example.meireles.banker.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    @Mapping(source = "addressRequest", target = "address")
    User toCustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(User addedUser);

}
