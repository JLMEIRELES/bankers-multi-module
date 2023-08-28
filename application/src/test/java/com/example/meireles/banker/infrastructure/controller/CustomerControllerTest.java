package com.example.meireles.banker.infrastructure.controller;

import com.example.meireles.banker.application.controller.v1.CustomerController;
import com.example.meireles.banker.application.dto.request.CustomerRequest;
import com.example.meireles.banker.application.dto.response.CustomerResponse;
import com.example.meireles.banker.application.mapper.CustomerDtoMapper;
import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.domain.service.CustomerService;
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
 * Unit Tests for @{@link CustomerController}
 */
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerDtoMapper customerDtoMapper;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    /**
     * Test method that verifies if {@link CustomerController#addCustomer(CustomerRequest)}
     * calls correctly the {@link CustomerService#addCustomer(Customer)} method, and create the customer
     */
    @Test
    void shouldCreateCustomer(){
        //given
        var customerRequest = mock(CustomerRequest.class);
        var customer = mock(Customer.class);
        var customerResponse = mock(CustomerResponse.class);

        when(customerDtoMapper.toCustomer(customerRequest)).thenReturn(customer);
        when(customerService.addCustomer(any(Customer.class))).thenReturn(customer);
        when(customerDtoMapper.toCustomerResponse(any(Customer.class))).thenReturn(customerResponse);
        when(customerResponse.getId()).thenReturn(1L);

        //when
        ResponseEntity<CustomerResponse> responseEntity = customerController.addCustomer(customerRequest);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(responseEntity),
                () -> Assertions.assertEquals(1L, Objects.requireNonNull(responseEntity.getBody()).getId()),
                () -> Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode())
        );
    }

    /**
     * Test method that verifies if {@link CustomerController#addCustomer(CustomerRequest)}
     * calls correctly the {@link CustomerService#addCustomer(Customer)} method, and throws an exception
     * when the service throws an exception
     */
    @Test
    void shouldNotCreateCustomer(){
        //given
        var customerRequest = mock(CustomerRequest.class);
        var customer = mock(Customer.class);

        when(customerDtoMapper.toCustomer(customerRequest)).thenReturn(customer);
        doThrow(RuntimeException.class).when(customerService).addCustomer(customer);


        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows(RuntimeException.class, () -> customerController.addCustomer(customerRequest)),
                () -> verify(customerService, times(1)).addCustomer(customer)
        );
    }
}
