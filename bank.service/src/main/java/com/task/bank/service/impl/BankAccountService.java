package com.task.bank.service.impl;

import com.task.bank.dto.BankAccountDto;

import java.util.UUID;

public interface BankAccountService {

    void createAccount(Long userId);

    void createPIN(UUID accountNumber, String pin);

    BankAccountDto getAccountInfo(Long userId);
}
