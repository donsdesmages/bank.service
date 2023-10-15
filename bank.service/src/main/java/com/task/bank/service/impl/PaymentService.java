package com.task.bank.service.impl;

import com.task.bank.dto.MoneyTransferDto;

public interface PaymentService {
    void updateBalance(MoneyTransferDto moneyTransferDto);
}
