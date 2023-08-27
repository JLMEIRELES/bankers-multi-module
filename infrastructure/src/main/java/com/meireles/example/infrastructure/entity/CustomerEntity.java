package com.meireles.example.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(unique = true, name = "document")
    private String document;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "bornDate")
    private LocalDate bornDate;

    @NotNull
    @Column(unique = true, name = "email")
    private String email;


}
