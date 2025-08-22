package com.cruzerdev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "acc_trans")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transId")
    private Long transactionId;

    private Long accountId;

    private double amount;
    @Column(name = "remainingBalance")
    private double remainingBalance;

    private AccountType accountType;

    @Column(name = "transactionTime")
    private LocalDateTime transTime;

}
