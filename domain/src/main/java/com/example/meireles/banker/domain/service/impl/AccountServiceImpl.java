package com.example.meireles.banker.domain.service.impl;

import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.provider.AccountProvider;
import com.example.meireles.banker.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountProvider accountProvider;

    @Override
    public Account addAccount(Account account) {
        return accountProvider.addAccount(account);
    }

}
