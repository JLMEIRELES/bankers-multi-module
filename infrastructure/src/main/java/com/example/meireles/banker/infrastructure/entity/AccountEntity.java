package com.example.meireles.banker.infrastructure.entity;

import com.example.meireles.banker.domain.model.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "digit")
    private Integer digit;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "balance")
    @NotNull
    private BigDecimal balance;
}
