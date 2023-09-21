package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.exception.AccountExistsException;
import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.model.User;
import com.example.meireles.banker.domain.model.enums.UserType;
import com.example.meireles.banker.domain.provider.AccountProvider;
import com.example.meireles.banker.domain.provider.UserProvider;
import com.example.meireles.banker.infrastructure.entity.AccountEntity;
import com.example.meireles.banker.infrastructure.entity.UserEntity;
import com.example.meireles.banker.infrastructure.mapper.AccountMapper;
import com.example.meireles.banker.infrastructure.mapper.UserMapper;
import com.example.meireles.banker.infrastructure.repository.AccountRepository;
import com.example.meireles.banker.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountAdapter implements AccountProvider {

    private static final int ACCOUNT_DIGIT_BOUND = 10;

    private static final int ACCOUNT_NUMBER_SIZE = 5;

    private final Random random;

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountMapper accountMapper;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Account addAccount(Account account) {
        User user = account.getUser();
        log.info("Verifying if customer with this document already exists");
        Optional<UserEntity> userEntityOptional = userRepository.findByDocument(user.getDocument());
        List<AccountEntity> accounts = new ArrayList<>();

        if (userEntityOptional.isPresent()) {
            log.info("customer found. Customer = {}", userEntityOptional);
            accounts = userEntityOptional.get().getAccounts();
            if (accounts.stream().anyMatch(a -> a.getAccountType().equals(account.getAccountType()))) {
                log.error("Customer have already an {} account", account.getAccountType());
                throw new AccountExistsException
                        (String.format("The customer have already a %s account", account.getAccountType().name()));
            }
            UserEntity userEntity = userEntityOptional.get();
            user.setUserType(userMapper.toUserType(userEntity.getUserType()));
            user.setId(userEntityOptional.get().getId());
            var addressEntity = userEntity.getAddress();
            user.getAddress().setId(addressEntity.getId());
        } else {
            user.setUserType(UserType.CUSTOMER);
        }

        log.info("Saving or updating customer. Customer = {}", user);
        User createdUser = userProvider.addUser(user);
        log.info("Customer saved. Customer = {}", createdUser);
        user.setId(createdUser.getId());

        log.info("Verifying if customer has already an account, with another type");
        Optional<AccountEntity> firstAccount = accounts.stream().findFirst();
        if (firstAccount.isEmpty()) {
            log.info("Account not founding. Generating new number and digit");
            generateNumberAndDigit(account);
        } else {
            log.info("Account founded. Account = {}. Setting new account the same number and digit.", firstAccount);
            account.setDigit(firstAccount.get().getDigit());
            account.setNumber(firstAccount.get().getNumber());
        }

        account.setUser(user);
        log.info("Saving Account = {}", account);
        AccountEntity accountEntity = accountRepository.save(accountMapper.toAccountEntity(account));
        log.info("Account saved. Account = {}", accountEntity);
        return accountMapper.toAccount(accountEntity);
    }

    /**
     * generated the number and digit of the account
     * @param account the account
     */
    private void generateNumberAndDigit(Account account) {
        account.setDigit(random.nextInt(ACCOUNT_DIGIT_BOUND));
        account.setNumber(RandomStringUtils.randomNumeric(ACCOUNT_NUMBER_SIZE));
        if (accountRepository.findByNumberAndType(account) != null) {
            generateNumberAndDigit(account);
        }
    }
}
