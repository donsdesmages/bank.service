package com.task.bank.controller;

import com.task.bank.dto.BankAccountDto;
import com.task.bank.service.impl.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBankAccount(@RequestParam Long userId) {
        bankAccountService.createAccount(userId);
    }

    @PutMapping("/pin")
    @ResponseStatus(HttpStatus.CREATED)
    public void createdPin(@RequestParam UUID accountNumber,
                           @RequestParam String pin) {
        bankAccountService.createPIN(accountNumber, pin);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BankAccountDto getAccountInfo(@RequestParam Long userId) {
        return bankAccountService.getAccountInfo(userId);
    }


}
