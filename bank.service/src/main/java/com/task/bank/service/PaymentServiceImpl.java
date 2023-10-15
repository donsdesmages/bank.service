package com.task.bank.service;

import com.task.bank.dto.MoneyTransferDto;
import com.task.bank.entity.BankAccountEntity;
import com.task.bank.exception.InsufficientMoneyException;
import com.task.bank.exception.NotFoundAccountException;
import com.task.bank.exception.UnauthorizedException;
import com.task.bank.repository.AccountRepository;
import com.task.bank.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void updateBalance(MoneyTransferDto moneyTransferDto) {
        Optional<BankAccountEntity> possibleSenderAccount = accountRepository.
            findBankAccountEntityByAccountNumber(moneyTransferDto.getSenderAccountNumber());

        if (possibleSenderAccount.isPresent()) {
            BankAccountEntity senderAccount = possibleSenderAccount.get();
            BigDecimal currentSenderBalance = senderAccount.getBalance();

            if (!moneyTransferDto.getSenderPin().equals(senderAccount.getPinCode())) {
                throw new UnauthorizedException("Invalid pin code");
            }

            if (currentSenderBalance.subtract(moneyTransferDto.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
                throw new InsufficientMoneyException("Sorry guy");
            }

            Optional<BankAccountEntity> possibleReceiverAccount = accountRepository
                .findBankAccountEntityByAccountNumber(moneyTransferDto.getReceiverAccountNumber());

            if (possibleReceiverAccount.isPresent()) {
                BankAccountEntity receiverAccount = possibleReceiverAccount.get();

                BigDecimal currentReceiverBalance = receiverAccount.getBalance();
                BigDecimal newReceiverBalance = currentReceiverBalance.add(moneyTransferDto.getAmount());

                BigDecimal newSenderBalance = currentSenderBalance.subtract(moneyTransferDto.getAmount());

                senderAccount.setBalance(newSenderBalance);
                receiverAccount.setBalance(newReceiverBalance);

                accountRepository.save(senderAccount);
                accountRepository.save(receiverAccount);
            } else {
                throw new NotFoundAccountException("Receiver account with id %s not found"
                    .formatted(moneyTransferDto.getReceiverAccountNumber().toString()));
            }
        } else {
            throw new NotFoundAccountException("Sender account with id %s not found"
                .formatted(moneyTransferDto.getSenderAccountNumber().toString()));
        }
    }
}

