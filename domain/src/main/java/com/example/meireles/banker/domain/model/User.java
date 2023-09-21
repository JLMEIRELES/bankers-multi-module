package com.example.meireles.banker.domain.model;

import com.example.meireles.banker.domain.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String document;
    private String name;
    private LocalDate bornDate;
    private String email;
    private Address address;
    private String password;
    private UserType userType;
}
