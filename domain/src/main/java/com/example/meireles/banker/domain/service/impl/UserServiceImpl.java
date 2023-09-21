package com.example.meireles.banker.domain.service.impl;

import com.example.meireles.banker.domain.model.User;
import com.example.meireles.banker.domain.model.enums.UserType;
import com.example.meireles.banker.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.meireles.banker.domain.provider.UserProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserProvider userProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public User addCustomer(User user) {
        log.info("Creating customer = {}", user);
        user.setUserType(UserType.CUSTOMER);
        return userProvider.addUser(user);
    }
}
