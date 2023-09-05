package com.example.meireles.banker.domain.service;

import com.example.meireles.banker.domain.model.Account;

public interface AccountService {

    /**
     * Creates a new account
     *
     * @param account the customer to be created
     * @return the created account
     */
    Account addAccount(Account account);
}
