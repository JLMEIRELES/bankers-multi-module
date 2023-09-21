package com.example.meireles.banker.application.controller.v1;

import com.example.meireles.banker.application.dto.request.AuthenticationRequest;
import com.example.meireles.banker.application.mapper.AuthenticationMapper;
import com.example.meireles.banker.domain.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${user.controller}", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final AuthenticationService authenticationService;

    private final AuthenticationMapper authenticationMapper;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authRequest){
        String token = authenticationService.authenticate(authenticationMapper.toUser(authRequest));
        return ResponseEntity.ok(token);
    }

}
