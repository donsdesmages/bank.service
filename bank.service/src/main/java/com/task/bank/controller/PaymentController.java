package com.task.bank.controller;

import com.task.bank.dto.MoneyTransferDto;
import com.task.bank.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendMoney(@RequestBody MoneyTransferDto moneyTransferDto) {
        paymentService.updateBalance(moneyTransferDto);
    }
}
