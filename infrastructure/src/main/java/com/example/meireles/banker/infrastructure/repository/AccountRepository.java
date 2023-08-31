package com.example.meireles.banker.infrastructure.repository;

import com.example.meireles.banker.domain.model.Account;
import com.example.meireles.banker.infrastructure.entity.AccountEntity;
import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("SELECT account FROM AccountEntity account WHERE account.number = :#{#account.number} " +
            "AND account.digit = :#{#account.digit} AND account.accountType = :#{#account.accountType}")
    AccountEntity findByNumberAndType(@Param("account") Account account);

    @Query("SELECT a FROM AccountEntity a " +
            "INNER JOIN a.customer customer " +
            "WHERE a.customer = :customer")
    List<AccountEntity> findByCustomer(@Param("customer") CustomerEntity customer);
}
