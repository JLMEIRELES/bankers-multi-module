package com.example.meireles.banker.application.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${user.controller}", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public ResponseEntity<?> login(){
        return ResponseEntity.ok().build();
    }

}
