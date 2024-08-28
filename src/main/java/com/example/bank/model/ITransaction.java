package com.example.bank.model;

import com.example.bank.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransaction {

    //Insert new transaction
    Transaction newTransaction(Long account, Transaction transaction);

    //Get all account transactions
    List<Transaction> getAllAccountTransactions();

    //Get a unique account transaction
    List<Transaction> getUniqueAccountTransaction();
}
