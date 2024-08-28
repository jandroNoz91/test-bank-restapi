package com.example.bank.repository;

import com.example.bank.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction (id, account, amount, type, date)" +
            " VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void insertTransaction(Long id,
                           Long account,
                           double amount,
                           String type,
                           LocalDateTime date);

}
