package com.example.meireles.banker.domain.provider;

import com.example.meireles.banker.domain.model.Account;

public interface AccountProvider {

    /**
     * Saves an Account in database
     *
     * @param account the account to be saved
     * @return the created account
     */
    Account addAccount(Account account);
}
