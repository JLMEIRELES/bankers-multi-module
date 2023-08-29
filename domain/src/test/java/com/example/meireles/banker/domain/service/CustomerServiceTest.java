package com.example.meireles.banker.domain.service;

import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.provider.CustomerProvider;
import com.example.meireles.banker.domain.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerProvider customerProvider;

    /**
     * Checks if method addCostumer calls {@link CustomerProvider#addCustomer(Customer)} correctly
     */
    @Test
    void shouldCreateCustomer(){
        //given
        var customer = spy(Customer.builder().name("name").build());

        when(customerProvider.addCustomer(any(Customer.class))).thenReturn(customer);

        //when
        Customer createdCustomer = customerService.addCustomer(customer);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdCustomer),
                () -> Assertions.assertEquals("name", createdCustomer.getName()),
                () -> Assertions.assertEquals(customer, createdCustomer),
                () -> verify(customerProvider, times(1)).addCustomer(customer)
        );
    }

    /**
     * Checks if method addCostumer throws an exception
     * if {@link CustomerProvider#addCustomer(Customer)} throws an exception
     */
    @Test
    void shouldNotCreateCustomer(){
        //given
        var customer = mock(Customer.class);

        doThrow(RuntimeException.class).when(customerProvider).addCustomer(any(Customer.class));

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows( RuntimeException.class, () -> customerService.addCustomer(customer)),
                () -> verify(customerProvider, times(1)).addCustomer(customer)
        );
    }
}
