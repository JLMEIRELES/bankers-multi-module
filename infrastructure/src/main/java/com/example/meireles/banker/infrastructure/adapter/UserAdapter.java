package com.example.meireles.banker.infrastructure.adapter;

import com.example.meireles.banker.domain.model.Address;
import com.example.meireles.banker.domain.model.User;
import com.example.meireles.banker.domain.provider.UserProvider;
import com.example.meireles.banker.infrastructure.client.ZipCodeClient;
import com.example.meireles.banker.infrastructure.client.dto.Endereco;
import com.example.meireles.banker.infrastructure.entity.UserEntity;
import com.example.meireles.banker.infrastructure.mapper.UserMapper;
import com.example.meireles.banker.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserAdapter implements UserProvider{

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected ZipCodeClient zipCodeClient;

    @Autowired
    protected PasswordEncoder encoder;

    @Autowired
    protected UserRepository userRepository;

    public User addUser(User user) {
        Address customerAddress = user.getAddress();

        log.info("Searching for address with zip code = {}", user.getAddress().getZipCode());
        Endereco endereco = zipCodeClient.
                getAddress(user.getAddress().getZipCode());
        var address = userMapper.toAddress(endereco);
        log.info("Address founded. Address = {}", address);

        Address mergedAddress = userMapper.mergeAddress(customerAddress, address);
        user.setAddress(mergedAddress);

        log.info("Saving Customer = {} in database", user);
        UserEntity userEntity = encodePassword(userMapper.toUserEntity(user));
        log.info("Customer = {} was saved", userEntity);
        return userMapper.toUser(userRepository.save(userEntity));
    }

    public UserEntity encodePassword(UserEntity userEntity) {
        userEntity.setUserPassword(encoder.encode(userEntity.getPassword()));
        return userEntity;
    }
}
