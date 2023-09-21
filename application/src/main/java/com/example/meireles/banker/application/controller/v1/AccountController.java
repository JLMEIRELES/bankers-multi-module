package com.example.meireles.banker.application.controller.v1;

import com.example.meireles.banker.application.dto.request.AccountRequest;
import com.example.meireles.banker.application.dto.response.AccountResponse;
import com.example.meireles.banker.application.mapper.AccountDtoMapper;
import com.example.meireles.banker.application.utils.validations.annotation.auth.AdmEndpoint;
import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Class rest controller to manage {@link AccountController} endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${account.controller}", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Account Controller", description = "Account management API")
public class AccountController {

    @Value("${account.controller}")
    private String path;

    private final AccountDtoMapper accountDtoMapper;

    private final AccountService accountService;

    /**
     * Creates a new account
     *
     * @param accountRequest an instance of {@link AccountRequest} content information about the account to be created
     * @return an instance of {@link AccountResponse} content the number and digit of the created account
     */
    @PostMapping
    @AdmEndpoint
    @Operation(summary = "Create a new account", description = "Create a new account and update or create it's costumer")
    public ResponseEntity<AccountResponse> addAccount(@Valid @RequestBody AccountRequest accountRequest){
        Account account = accountService.addAccount(accountDtoMapper.toAccount(accountRequest));
        AccountResponse accountResponse = accountDtoMapper.toAccountResponse(account);
        return ResponseEntity.created((URI.create(path + "/" + accountResponse.getNumber())))
                .body(accountResponse);
    }

}
