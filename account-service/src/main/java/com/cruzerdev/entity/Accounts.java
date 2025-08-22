package com.cruzerdev.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usr_acc")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "totalBalance")
    private double balance;

    @Column(name = "accountHolderName")
    private String accountHolderName;
}
