package com.cruzerdev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEvent {
    private String accountId;
    private String type;
    private double amount;
    private double remainingBalance;
    private long  createAt;

}
