package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Address;
import com.example.meireles.banker.domain.model.Customer;
import com.example.meireles.banker.infrastructure.client.ZipCodeClient;
import com.example.meireles.banker.infrastructure.client.dto.Endereco;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import com.example.meireles.banker.infrastructure.mapper.CustomerMapper;
import com.example.meireles.banker.infrastructure.mapper.util.ReflectionMapper;
import com.example.meireles.banker.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerAdapterTest {

    @InjectMocks
    private CustomerAdapter customerAdapter;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ZipCodeClient zipCodeClient;

    @Mock
    private ReflectionMapper reflectionMapper;

    /**
     * Checks if Customer adapter are correctly saving Customer, calling {@link CustomerRepository#save(Object)}
     */
    @Test
    void shouldSaveCustomer(){
        //given
        Customer customer = mock(Customer.class);
        CustomerEntity customerEntity = mock(CustomerEntity.class);
        Address address = spy(Address.builder().
                zipCode("zipCode").
                build()
        );
        Endereco endereco = mock(Endereco.class);


        when(customer.getAddress()).thenReturn(address);
        when(customerMapper.toCustomerEntity(any(Customer.class))).thenReturn(customerEntity);
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);
        when(customerMapper.toCustomer(any(CustomerEntity.class))).thenReturn(customer);
        when(zipCodeClient.getAddress(any(String.class))).thenReturn(endereco);
        when(customerMapper.toAddress(endereco)).thenReturn(address);

        //when
        Customer createdCustomer = customerAdapter.addCustomer(customer);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdCustomer),
                () -> Assertions.assertEquals(customer, createdCustomer),
                () -> verify(customerRepository, times(1)).save(customerEntity)
        );
    }

    /**
     * Checks if Customer adapter throws and exception, when {@link CustomerRepository#save(Object)}
     * throws an exception
     */
    @Test
    void shouldNotSaveCustomer(){
        //given
        Customer customer = mock(Customer.class);
        CustomerEntity customerEntity = mock(CustomerEntity.class);
        Address address = spy(Address.builder().
                zipCode("zipCode").
                build()
        );
        Endereco endereco = mock(Endereco.class);

        when(customer.getAddress()).thenReturn(address);
        when(customerMapper.toCustomerEntity(any(Customer.class))).thenReturn(customerEntity);
        when(zipCodeClient.getAddress(any(String.class))).thenReturn(endereco);
        when(customerMapper.toAddress(endereco)).thenReturn(address);
        doThrow(RuntimeException.class).when(customerRepository).save(customerEntity);

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows(RuntimeException.class, () -> customerAdapter.addCustomer(customer)),
                () -> verify(customerRepository, times(1)).save(customerEntity)
        );
    }

}
