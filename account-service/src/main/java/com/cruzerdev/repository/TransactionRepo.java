package com.cruzerdev.repository;

import com.cruzerdev.entity.AccountTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<AccountTransactions, Long> {
}
