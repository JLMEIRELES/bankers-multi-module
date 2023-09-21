package com.example.meireles.banker.infrastructure.entity;

import com.example.meireles.banker.infrastructure.entity.enums.UserTypeAuthority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="\"user\"")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, name = "document")
    private String document;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "born_date")
    private LocalDate bornDate;

    @NotNull
    @Column(unique = true, name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String userPassword;

    @Column(name = "user_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserTypeAuthority userType;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AddressEntity address;

    @OneToMany(mappedBy = "user")
    private List<AccountEntity> accounts;


    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;


    @PrePersist
    @PreUpdate
    public void setAddressCustomer(){
        this.address.setUser(this);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.getUserType());
    }

    @Override
    public String getPassword() {
        return this.getUserPassword();
    }

    @Override
    public String getUsername() {
        return this.getDocument();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
