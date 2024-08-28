package com.example.bank.controller;

import com.example.bank.model.Transaction;
import com.example.bank.model.TransactionResponse;
import com.example.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class TransactionController {

    @Autowired
    TransactionService service;

    //Insert new transaction
    @PostMapping("/{account-number}/transaction")
    public ResponseEntity<TransactionResponse> saveTransaction(@PathVariable(name = "account-number") Long account,
                                                      @RequestBody Transaction transaction) {
        try{
            Transaction transactionObj = service.newTransaction(account,transaction);
            if (transactionObj.getId()!= null) {
                return new ResponseEntity<>(new TransactionResponse("Saved successfully", transactionObj), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new TransactionResponse("Error on saving",null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e){
            return new ResponseEntity<>(new TransactionResponse("Internal error",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Get all account transactions
    @GetMapping("/{account-number}/transaction")
    public ResponseEntity<TransactionResponse> getAllAccountTransactions(@PathVariable(name = "account-number") Long account) {
        try {
            List<Transaction> transactionList = service.getAllAccountTransactions()
                    .stream().filter(x -> x.getAccount().equals(account)).toList();
            if (transactionList.isEmpty()){
                return new ResponseEntity<>(new TransactionResponse("Not found",null),HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(new TransactionResponse("Found transactions",transactionList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new TransactionResponse("Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get a unique account transaction
    @GetMapping("/{account-number}/transaction/{id}")
    public ResponseEntity<TransactionResponse> getUniqueAccountTransaction(@PathVariable(name = "account-number") Long account,
                                                                           @PathVariable(name = "id") Long id) {
        try {
            Optional<Transaction> transactionObj = service.getUniqueAccountTransaction()
                    .stream().filter(x -> x.getAccount().equals(account) && x.getId().equals(id)).findFirst();
            if (transactionObj.isPresent()){
                return new ResponseEntity<>(new TransactionResponse("Found transaction",transactionObj), HttpStatus.OK);
            }
            return new ResponseEntity<>(new TransactionResponse("Transaction not found",null),HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(new TransactionResponse("Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    //---------------------TESTs---------------------
    //Initial loading
    @PostMapping("/transaction/loading")
    public ResponseEntity<TransactionResponse> loadAllRecords(){
        if(service.loadRecords().equals("ok")){
            return new ResponseEntity<>(new TransactionResponse("Saved successfully",service.getAllTransactions()), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(new TransactionResponse("Error",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Check status
    @GetMapping("/service")
    public ResponseEntity<TransactionResponse> checkConnected(){
        return new ResponseEntity<>(new TransactionResponse("Connected",null), HttpStatus.OK);
    }

    //Get all transactions
    @GetMapping("/transaction")
    public ResponseEntity<TransactionResponse> getAllTransactions(@RequestHeader("Authorization") String token) {
        if (token.equals("IBM1234")) {
            try {
                List<Transaction> transactionList = service.getAllTransactions();
                if (transactionList.isEmpty()){
                    return new ResponseEntity<>(new TransactionResponse("Not found",null),HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(new TransactionResponse("Found transactions",transactionList), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new TransactionResponse("Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new TransactionResponse("Not authorization",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get a unique account transaction
    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable(name = "id") Long id){
        return service.getUniqueTransaction(id).map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
