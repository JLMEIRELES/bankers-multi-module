package com.example.meireles.banker.infrastructure.entity;

import com.example.meireles.banker.domain.model.enums.CustomerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "user_id")
public class CustomerEntity extends UserEntity {

    @OneToMany(mappedBy = "customer")
    private List<AccountEntity> accounts;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    @NotNull
    private CustomerType customerType;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
