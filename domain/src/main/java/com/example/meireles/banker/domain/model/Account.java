package com.example.meireles.banker.domain.model;

import com.example.meireles.banker.domain.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    private AccountType accountType;
    private User user;
    private String number;
    private Integer digit;
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;
}
