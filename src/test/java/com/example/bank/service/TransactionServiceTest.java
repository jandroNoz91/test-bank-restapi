package com.example.bank.service;

import com.example.bank.model.Transaction;
import com.example.bank.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TransactionServiceTest {
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() throws Exception {
//        MockitoAnnotations.openMocks(this);
        transactionRepository = Mockito.mock(TransactionRepository.class);
    }

    @Test
    @DisplayName("Test repository")
    void getTransaction() {
        Transaction transaction = new Transaction();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        transaction.setId(1L);
        transaction.setAccount(1188274026L);
        transaction.setAmount(150.50);
        transaction.setType("deposit");
        transaction.setDate(LocalDateTime.parse("2024-04-09 23:08:04.270", formatter));

        when(transactionRepository.findById(anyLong())).thenReturn(Optional.of(transaction));
    }

}