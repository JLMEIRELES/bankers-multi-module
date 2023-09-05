package com.example.meireles.banker.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_id_seq", allocationSize = 1)
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

    @OneToMany(mappedBy = "customer")
    private List<AccountEntity> accounts;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

}
