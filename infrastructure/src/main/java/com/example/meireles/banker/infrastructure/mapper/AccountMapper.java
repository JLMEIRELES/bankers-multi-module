package com.example.meireles.banker.infrastructure.mapper;

import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.infrastructure.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity toAccountEntity(Account account);

    Account toAccount(AccountEntity accountEntity);
}
