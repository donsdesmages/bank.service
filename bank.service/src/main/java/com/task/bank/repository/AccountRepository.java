package com.task.bank.repository;

import com.task.bank.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<BankAccountEntity, Long> {
    Optional<BankAccountEntity> findBankAccountEntityByAccountNumber(UUID accountNumber);
}
