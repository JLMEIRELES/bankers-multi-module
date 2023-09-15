package com.example.meireles.banker.infrastructure.repository;

import com.example.meireles.banker.infrastructure.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM CustomerEntity c LEFT JOIN FETCH c.accounts WHERE c.document = :document")
    Optional<CustomerEntity> findByDocument(@Param("document") String document);

}
