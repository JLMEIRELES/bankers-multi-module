package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.infrastructure.entity.AccountEntity;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import com.example.meireles.banker.infrastructure.mapper.AccountMapper;
import com.example.meireles.banker.infrastructure.repository.AccountRepository;
import com.example.meireles.banker.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountAdapterTest {

    @InjectMocks
    private AccountAdapter accountAdapter;

    @Mock
    private CustomerAdapter customerAdapter;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Random random;

    /**
     * Checks if {@link AccountAdapter#addAccount(Account)} are correctly saving an account,
     * calling {@link AccountRepository#save(Object)}
     */
    @Test
    void shouldSaveAccount(){
        //given
        Account account = mock(Account.class);
        AccountEntity accountEntity = mock(AccountEntity.class);
        Customer customer = spy(Customer.builder().
                name("customer").
                email("email@email.com").
                bornDate(LocalDate.now()).
                document("12345678910").
                build()
                );
        CustomerEntity customerEntity = mock(CustomerEntity.class);

        when(random.nextInt(anyInt())).thenReturn(1);
        when(account.getCustomer()).thenReturn(customer);
        when(customerRepository.findByDocument(any(String.class))).thenReturn(Optional.ofNullable(customerEntity));
        when(accountMapper.toAccountEntity(any(Account.class))).thenReturn(accountEntity);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountEntity);
        when(accountMapper.toAccount(any(AccountEntity.class))).thenReturn(account);
        when(customerAdapter.addCustomer(any(Customer.class))).thenReturn(customer);
        doReturn(1L).when(customerEntity).getId();

        //when
        Account createdAccount = accountAdapter.addAccount(account);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdAccount),
                () -> Assertions.assertEquals(account, createdAccount),
                () -> verify(accountRepository, times(1)).save(accountEntity)
        );
    }

    /**
     * Checks if {@link AccountAdapter#addAccount(Account)} throws and exception,
     * when {@link AccountRepository#save(Object)} throws an exception
     */
    @Test
    void shouldNotSaveCustomer(){
        //given
        Account account = mock(Account.class);
        AccountEntity accountEntity = mock(AccountEntity.class);
        CustomerEntity customerEntity = mock(CustomerEntity.class);
        Customer customer = spy(Customer.builder().
                name("customer").
                email("email@email.com").
                bornDate(LocalDate.now()).
                document("12345678910").
                build()
        );

        when(random.nextInt(anyInt())).thenReturn(1);
        when(account.getCustomer()).thenReturn(customer);
        when(customerRepository.findByDocument(any(String.class))).thenReturn(Optional.ofNullable(customerEntity));
        when(accountMapper.toAccountEntity(any(Account.class))).thenReturn(accountEntity);
        when(customerAdapter.addCustomer(any(Customer.class))).thenReturn(customer);
        doReturn(1L).when(customerEntity).getId();
        doThrow(RuntimeException.class).when(accountRepository).save(accountEntity);

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows(RuntimeException.class, () -> accountAdapter.addAccount(account)),
                () -> verify(accountRepository, times(1)).save(accountEntity)
        );
    }

}
