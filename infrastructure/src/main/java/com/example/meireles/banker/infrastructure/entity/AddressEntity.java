package com.example.meireles.banker.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class AddressEntity {

    @Id
    @Column(name = "customer_id")
    private Long id;

    @NotNull
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "neighborhood")
    private String neighborhood;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "state")
    private String state;

    @Column(name = "complement")
    private String complement;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
