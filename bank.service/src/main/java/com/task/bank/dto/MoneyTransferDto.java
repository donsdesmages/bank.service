package com.task.bank.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class MoneyTransferDto {
    private final UUID senderAccountNumber;
    private final String senderPin;
    private final BigDecimal amount;

    private final UUID receiverAccountNumber;
}
