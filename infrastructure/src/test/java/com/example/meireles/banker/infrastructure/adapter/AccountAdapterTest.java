package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.domain.model.User;
import com.example.meireles.banker.infrastructure.entity.AccountEntity;
import com.example.meireles.banker.infrastructure.mapper.AccountMapper;
import com.example.meireles.banker.infrastructure.mapper.UserMapper;
import com.example.meireles.banker.infrastructure.repository.AccountRepository;
import com.example.meireles.banker.infrastructure.repository.UserRepository;
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
    private UserAdapter userAdapter;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

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
        User user = spy(User.builder().
                name("customer").
                email("email@email.com").
                bornDate(LocalDate.now()).
                document("12345678910").
                build()
                );

        when(random.nextInt(anyInt())).thenReturn(1);
        when(account.getUser()).thenReturn(user);
        when(userRepository.findByDocument(any(String.class))).thenReturn(Optional.empty());
        when(accountMapper.toAccountEntity(any(Account.class))).thenReturn(accountEntity);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountEntity);
        when(accountMapper.toAccount(any(AccountEntity.class))).thenReturn(account);
        when(userAdapter.addUser(any(User.class))).thenReturn(user);

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
        User user = spy(User.builder().
                name("customer").
                email("email@email.com").
                bornDate(LocalDate.now()).
                document("12345678910").
                build()
        );

        when(random.nextInt(anyInt())).thenReturn(1);
        when(account.getUser()).thenReturn(user);
        when(userRepository.findByDocument(any(String.class))).thenReturn(Optional.empty());
        when(accountMapper.toAccountEntity(any(Account.class))).thenReturn(accountEntity);
        when(userAdapter.addUser(any(User.class))).thenReturn(user);
        doThrow(RuntimeException.class).when(accountRepository).save(accountEntity);

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows(RuntimeException.class, () -> accountAdapter.addAccount(account)),
                () -> verify(accountRepository, times(1)).save(accountEntity)
        );
    }

}
