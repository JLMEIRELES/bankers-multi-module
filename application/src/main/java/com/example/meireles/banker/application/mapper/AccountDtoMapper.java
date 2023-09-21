package com.example.meireles.banker.application.mapper;

import com.example.meireles.banker.application.dto.request.AccountRequest;
import com.example.meireles.banker.application.dto.response.AccountResponse;
import com.example.meireles.banker.domain.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    @Mapping(source = "customerRequest", target = "user")
    @Mapping(source = "customerRequest.addressRequest", target = "user.address")
    Account toAccount(AccountRequest request);

    AccountResponse toAccountResponse(Account account);

}
