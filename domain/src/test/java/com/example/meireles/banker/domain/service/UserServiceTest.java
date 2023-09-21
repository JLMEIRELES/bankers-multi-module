package com.example.meireles.banker.domain.service;

import com.example.meireles.banker.domain.model.User;
import com.example.meireles.banker.domain.provider.UserProvider;
import com.example.meireles.banker.domain.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl customerService;

    @Mock
    private UserProvider userProvider;

    /**
     * Checks if method addCostumer calls {@link UserProvider#addUser(User)} correctly
     */
    @Test
    void shouldCreateCustomer(){
        //given
        var customer = spy(User.builder().name("name").build());

        when(userProvider.addUser(any(User.class))).thenReturn(customer);

        //when
        User createdUser = customerService.addCustomer(customer);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdUser),
                () -> Assertions.assertEquals("name", createdUser.getName()),
                () -> Assertions.assertEquals(customer, createdUser),
                () -> verify(userProvider, times(1)).addUser(customer)
        );
    }

    /**
     * Checks if method addCostumer throws an exception
     * if {@link UserProvider#addUser(User)} throws an exception
     */
    @Test
    void shouldNotCreateCustomer(){
        //given
        var customer = mock(User.class);

        doThrow(RuntimeException.class).when(userProvider).addUser(any(User.class));

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows( RuntimeException.class, () -> customerService.addCustomer(customer)),
                () -> verify(userProvider, times(1)).addUser(customer)
        );
    }
}
