package com.example.meireles.banker.domain.service;

import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.model.enums.AccountType;
import com.example.meireles.banker.domain.provider.AccountProvider;
import com.example.meireles.banker.domain.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountProvider accountProvider;

    /**
     * Checks if method {@link AccountService#addAccount(Account)} calls {@link AccountProvider} correctly
     */
    @Test
    void shouldCreateAccount(){
        //given
        var account = spy(Account.builder().accountType(AccountType.CURRENT).build());

        when(accountProvider.addAccount(any(Account.class))).thenReturn(account);

        //when
        Account createdAccount = accountService.addAccount(account);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdAccount),
                () -> Assertions.assertEquals(AccountType.CURRENT, account.getAccountType()),
                () -> Assertions.assertEquals(account, createdAccount),
                () -> verify(accountProvider, times(1)).addAccount(account)
        );
    }

    /**
     * Checks if method {@link AccountService#addAccount(Account)} throws an exception
     * if {@link AccountProvider#addAccount(Account)} throws an exception
     */
    @Test
    void shouldNotCreateAccount(){
        //given
        var account = mock(Account.class);

        doThrow(RuntimeException.class).when(accountProvider).addAccount(any(Account.class));

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows( RuntimeException.class, () -> accountService.addAccount(account)),
                () -> verify(accountProvider, times(1)).addAccount(account)
        );
    }
}
