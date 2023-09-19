package com.example.meireles.banker.infrastructure.entity;

import com.example.meireles.banker.domain.model.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "account_seq", sequenceName = "account_id_seq", allocationSize = 1)
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

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
