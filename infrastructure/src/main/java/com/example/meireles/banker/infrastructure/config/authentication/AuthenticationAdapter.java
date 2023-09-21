package com.example.meireles.banker.infrastructure.config.authentication;

import com.example.meireles.banker.infrastructure.entity.UserEntity;
import com.example.meireles.banker.infrastructure.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@Component
public class AuthenticationAdapter implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user;
        if (username.contains("@")){
            user = userRepository.findByEmail(username);
        } else{
            user = userRepository.findByDocument(username);
        }
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
