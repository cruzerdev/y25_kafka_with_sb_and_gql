package com.cruzerdev.service;

import com.cruzerdev.dto.TransactionEvent;
import com.cruzerdev.entity.AccountTransactions;
import com.cruzerdev.entity.AccountType;
import com.cruzerdev.entity.Accounts;
import com.cruzerdev.producer.TransactionProducer;
import com.cruzerdev.repository.AccountRepo;
import com.cruzerdev.repository.TransactionRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionProducer transactionProducer;

    @Transactional
    public String withdraw(String accountId, double amount) {

        Accounts accounts=accountRepo.findById(Long.valueOf(accountId))
                .orElseThrow(()-> new RuntimeException("Account ID Not found!!!"));

        if(accounts.getBalance()<amount){
            transactionProducer.sendNotificationOnInsufficientBalance(accounts.getAccountHolderName(),amount);
            throw new RuntimeException("Insufficient balance!!!");
        }
        accounts.setBalance(accounts.getBalance() - amount);
        accountRepo.save(accounts);

        LocalDateTime transTime=LocalDateTime.now();
        AccountTransactions transHistory= AccountTransactions.builder()
                .accountId(Long.valueOf(accountId))
                .amount(amount)
                .remainingBalance(accounts.getBalance())
                .accountType(AccountType.DEBIT)
                .transTime(transTime)
                .build();
        transactionRepo.save(transHistory);

        TransactionEvent event = new TransactionEvent();
        event.setAccountId(accountId);
        event.setType(String.valueOf(AccountType.DEBIT));
        event.setAmount(amount);
        event.setRemainingBalance(accounts.getBalance());
        event.setCreateAt(transTime);

        try {
            transactionProducer.sendTransactionsEvent(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return  "Withdrawl Success!! Remaining balance: "+accounts.getBalance();


    }
}
