package com.example.bank.service;

import com.example.bank.model.ITransaction;
import com.example.bank.model.Transaction;
import com.example.bank.repository.TransactionRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransaction {
    @Autowired
    private TransactionRepository repository;

    public Transaction newTransaction(Long account, @NotNull Transaction transaction) {
        transaction.setAccount(account);
        return repository.save(transaction);
    }

    public List<Transaction> getAllAccountTransactions() {
        return repository.findAll();
    }

    public List<Transaction> getUniqueAccountTransaction() {
        return repository.findAll();
    }



    //----------------TESTs----------------
    public String loadRecords(){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]");
            repository.insertTransaction(10000L, 1188274026L, 150.50, "deposit", LocalDateTime.parse("2024-04-09 22:55:41.017", formatter));
            repository.insertTransaction(10001L, 7264942193L, 182.80, "withdrawal", LocalDateTime.parse("2024-04-09 22:56:31.233", formatter));
            repository.insertTransaction(10002L, 6450736465L, 322.25, "withdrawal", LocalDateTime.parse("2024-04-09 22:57:19.443", formatter));
            repository.insertTransaction(10003L, 9813246376L, 75.99, "withdrawal", LocalDateTime.parse("2024-04-09 22:59:55.180", formatter));
            repository.insertTransaction(10004L, 1236932764L, 85.00, "deposit", LocalDateTime.parse("2024-04-09 23:00:22.113", formatter));
            repository.insertTransaction(10005L, 7264942193L, 62.41, "withdrawal", LocalDateTime.parse("2024-04-09 23:00:57.400", formatter));
            repository.insertTransaction(10006L, 6450736465L, 200.00, "deposit", LocalDateTime.parse("2024-04-09 23:01:59.573", formatter));
            repository.insertTransaction(10007L, 1188274026L, 210.00, "deposit", LocalDateTime.parse("2024-04-09 23:02:26.010", formatter));
            repository.insertTransaction(10008L, 1324783003L, 350.00, "withdrawal", LocalDateTime.parse("2024-04-09 23:02:54.193", formatter));
            repository.insertTransaction(10009L, 3846193763L, 233.15, "withdrawal", LocalDateTime.parse("2024-04-09 23:03:22.393", formatter));
            repository.insertTransaction(10010L, 2926452937L, 520.00, "withdrawal", LocalDateTime.parse("2024-04-09 23:04:24.153", formatter));
            repository.insertTransaction(10011L, 3726383792L, 230.50, "deposit", LocalDateTime.parse("2024-04-09 23:04:54.657", formatter));
            repository.insertTransaction(10012L, 8262538392L, 500.00, "deposit", LocalDateTime.parse("2024-04-09 23:05:18.623", formatter));
            repository.insertTransaction(10013L, 1324783003L, 80.75, "withdrawal", LocalDateTime.parse("2024-04-09 23:06:40.340", formatter));
            repository.insertTransaction(10014L, 1327625392L, 345.85, "withdrawal", LocalDateTime.parse("2024-04-09 23:07:15.513", formatter));
            repository.insertTransaction(10015L, 7264942193L, 1500.00, "deposit", LocalDateTime.parse("2024-04-09 23:08:04.270", formatter));

            return "ok";
        } catch (Exception e){
            return e.getMessage();
        }
    }

    public List<Transaction> getAllTransactions(){
        return repository.findAll();
    }

    public Optional<Transaction> getUniqueTransaction(Long id){
        return repository.findById(id);
    }
}
