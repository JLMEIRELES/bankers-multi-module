package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Address;
import com.example.meireles.banker.domain.model.User;
import com.example.meireles.banker.infrastructure.client.ZipCodeClient;
import com.example.meireles.banker.infrastructure.client.dto.Endereco;
import com.example.meireles.banker.infrastructure.entity.UserEntity;
import com.example.meireles.banker.infrastructure.mapper.UserMapper;
import com.example.meireles.banker.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {

    @InjectMocks
    private UserAdapter userAdapter;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ZipCodeClient zipCodeClient;

    /**
     * Checks if Customer adapter are correctly saving Customer, calling {@link UserRepository#save(Object)}
     */
    @Test
    void shouldSaveCustomer(){
        //given
        User user = mock(User.class);
        UserEntity userEntity = mock(UserEntity.class);
        Address address = spy(Address.builder().
                zipCode("zipCode").
                build()
        );
        Endereco endereco = mock(Endereco.class);


        when(user.getAddress()).thenReturn(address);
        when(userMapper.toUserEntity(any(User.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.toUser(any(UserEntity.class))).thenReturn(user);
        when(zipCodeClient.getAddress(any(String.class))).thenReturn(endereco);
        when(userMapper.toAddress(endereco)).thenReturn(address);

        //when
        User createdUser = userAdapter.addUser(user);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdUser),
                () -> Assertions.assertEquals(user, createdUser),
                () -> verify(userRepository, times(1)).save(userEntity)
        );
    }

    /**
     * Checks if Customer adapter throws and exception, when {@link UserRepository#save(Object)}
     * throws an exception
     */
    @Test
    void shouldNotSaveCustomer(){
        //given
        User user = mock(User.class);
        UserEntity userEntity = mock(UserEntity.class);
        Address address = spy(Address.builder().
                zipCode("zipCode").
                build()
        );
        Endereco endereco = mock(Endereco.class);

        when(user.getAddress()).thenReturn(address);
        when(userMapper.toUserEntity(any(User.class))).thenReturn(userEntity);
        when(zipCodeClient.getAddress(any(String.class))).thenReturn(endereco);
        when(userMapper.toAddress(endereco)).thenReturn(address);
        doThrow(RuntimeException.class).when(userRepository).save(userEntity);

        //when and then
        Assertions.assertAll(
                () -> Assertions.assertThrows(RuntimeException.class, () -> userAdapter.addUser(user)),
                () -> verify(userRepository, times(1)).save(userEntity)
        );
    }

}
