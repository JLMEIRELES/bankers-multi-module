package com.example.meireles.banker.infrastructure.controller;

import com.example.meireles.banker.application.controller.v1.AccountController;
import com.example.meireles.banker.application.dto.request.AccountRequest;
import com.example.meireles.banker.application.dto.response.AccountResponse;
import com.example.meireles.banker.application.mapper.AccountDtoMapper;
import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for {@link AccountController}
 */
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountDtoMapper accountDtoMapper;

    @Mock
    private AccountService accountService;


    /**
     * Test method that verifies if {@link AccountController#addAccount(AccountRequest)}
     * calls correctly the {@link AccountService#addAccount(Account)} method, and create the account
     */
    @Test
    void shouldCreateAnAccount(){
        //given
        var accountRequest = mock(AccountRequest.class);
        var account = mock(Account.class);
        var accountResponse = mock(AccountResponse.class);
        var accountNumber = "22222";

        when(accountDtoMapper.toAccount(accountRequest)).thenReturn(account);
        when(accountService.addAccount(any(Account.class))).thenReturn(account);
        when(accountDtoMapper.toAccountResponse(any(Account.class))).thenReturn(accountResponse);
        when(accountResponse.getNumber()).thenReturn(accountNumber);

        //when
        ResponseEntity<AccountResponse> responseEntity = accountController.addAccount(accountRequest);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(responseEntity),
                () -> Assertions.assertEquals(accountNumber, Objects.requireNonNull(responseEntity.getBody()).getNumber()),
                () -> Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode())
        );
    }

    /**
     * Test method that verifies if {@link AccountController#addAccount(AccountRequest)} 
     * calls correctly the {@link AccountService#addAccount(Account)} method, and throws an exception
     * when the service throws an exception
     */
    @Test
    void shouldNotCreateAnAccount(){
        //given
        var accountRequest = mock(AccountRequest.class);
        var account = mock(Account.class);

        when(accountDtoMapper.toAccount(accountRequest)).thenReturn(account);
        doThrow(RuntimeException.class).when(accountService).addAccount(account);

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows(RuntimeException.class, () -> accountController.addAccount(accountRequest)),
                () -> verify(accountService, times(1)).addAccount(account)
        );
    }

}
