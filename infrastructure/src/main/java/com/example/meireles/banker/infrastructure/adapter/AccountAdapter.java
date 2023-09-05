package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.exception.AccountExistsException;
import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.provider.AccountProvider;
import com.example.meireles.banker.infrastructure.entity.AccountEntity;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import com.example.meireles.banker.infrastructure.mapper.AccountMapper;
import com.example.meireles.banker.infrastructure.mapper.CustomerMapper;
import com.example.meireles.banker.infrastructure.repository.AccountRepository;
import com.example.meireles.banker.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AccountAdapter implements AccountProvider {

    private final Random random;

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    private final AccountMapper accountMapper;

    private final CustomerMapper customerMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Account addAccount(Account account) {
        Customer customer = account.getCustomer();
        Optional<CustomerEntity> customerEntity = customerRepository.findByDocument(customer.getDocument());
        List<AccountEntity> accounts = new ArrayList<>();

        if (customerEntity.isPresent()) {
            accounts = customerEntity.get().getAccounts();
            if (accounts.stream().anyMatch(a -> a.getAccountType().equals(account.getAccountType()))) {
                throw new AccountExistsException
                        (String.format("The customer have already a %s account", account.getAccountType().name()));
            }
            customer.setId(customerEntity.get().getId());
        }

        CustomerEntity createdCustomer = customerRepository.save(customerMapper.toCustomerEntity(customer));
        customer.setId(createdCustomer.getId());

        Optional<AccountEntity> firstAccount = accounts.stream().findFirst();
        if (firstAccount.isEmpty()) {
            generateNumberAndDigit(account);
        } else {
            account.setDigit(firstAccount.get().getDigit());
            account.setNumber(firstAccount.get().getNumber());
        }

        account.setCustomer(customer);
        AccountEntity accountEntity = accountRepository.save(accountMapper.toAccountEntity(account));
        return accountMapper.toAccount(accountEntity);
    }

    /**
     * generated the number and digit of the account
     * @param account the account
     */
    private void generateNumberAndDigit(Account account) {
        account.setDigit(random.nextInt(10));
        account.setNumber(RandomStringUtils.randomNumeric(5));
        if (accountRepository.findByNumberAndType(account) != null) {
            generateNumberAndDigit(account);
        }
    }
}
