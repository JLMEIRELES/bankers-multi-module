package com.example.meireles.banker.domain.service.impl;

import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.provider.AccountProvider;
import com.example.meireles.banker.domain.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountProvider accountProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public Account addAccount(Account account) {
        log.info("Adding account = {}", account);
        return accountProvider.addAccount(account);
    }

}
