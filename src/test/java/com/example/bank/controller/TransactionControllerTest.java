package com.example.bank.controller;

import com.example.bank.model.Transaction;
import com.example.bank.model.TransactionResponse;
import com.example.bank.service.TransactionService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionControllerTest {

    private TransactionController transactionController;
    private  TransactionController controller;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        controller = Mockito.mock(TransactionController.class);
        transactionController = new TransactionController();
        TransactionResponse transactionResponse = new TransactionResponse();
        transaction = new Transaction();
    }

    @Test
    @DisplayName("Test connection to service")
    public void testCheckConnectedService(){
        ResponseEntity<TransactionResponse> expectedResponseEntity = new ResponseEntity<>(
                new TransactionResponse("Connected",null), HttpStatus.OK);

        when(controller.checkConnected()).thenReturn(expectedResponseEntity);
    }

    @Test
    @DisplayName("Test new transaction")
    public void testSaveNewTransaction(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        transaction.setAccount(1188274026L);
        transaction.setAmount(150.50);
        transaction.setType("deposit");
        transaction.setDate(LocalDateTime.parse("2024-04-09 23:08:04.270", formatter));

        ResponseEntity<TransactionResponse> expectedResponseEntity = new ResponseEntity<>(
                new TransactionResponse("Saved successfully", transaction), HttpStatus.OK);

        when(controller.saveTransaction(anyLong(),any(Transaction.class))).thenReturn(expectedResponseEntity);
    }

    @Test
    @DisplayName("Test error service")
    public void testErrorService(){
        ResponseEntity<TransactionResponse> answerResponseEntity = transactionController.saveTransaction(anyLong(),any(Transaction.class));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(answerResponseEntity.getStatusCode()));
    }

    @Test
    @Disabled
    @DisplayName("Test get all account transactions")
    public void testGetAllAccountTransactions(){
        List<Transaction> transactionList = new ArrayList<Transaction>();

        ResponseEntity<TransactionResponse> expectedResponseEntity = new ResponseEntity<>(
                new TransactionResponse("Found transactions", transactionList), HttpStatus.OK);

        when(controller.getAllAccountTransactions(anyLong())).thenReturn(expectedResponseEntity);
    }

    @AfterEach
    void tearDown() {
    }


}