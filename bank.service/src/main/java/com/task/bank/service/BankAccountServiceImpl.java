package com.task.bank.service;

import com.task.bank.dto.BankAccountDto;
import com.task.bank.entity.BankAccountEntity;
import com.task.bank.entity.UserEntity;
import com.task.bank.exception.NotFoundAccountException;
import com.task.bank.repository.AccountRepository;
import com.task.bank.repository.UserRepository;
import com.task.bank.service.impl.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private static final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(0);

    @Override
    public void createAccount(Long userId) {
        Optional<UserEntity> possibleUser = userRepository.findUserEntityById(userId);
        BankAccountEntity account = new BankAccountEntity();

        possibleUser.ifPresent((user) -> {
            account.setAccountNumber(UUID.randomUUID());
            account.setBalance(INITIAL_BALANCE);
            account.setUser(user);

            accountRepository.save(account);
        });

    }

    @Override
    public BankAccountDto getAccountInfo(Long userId) {
        Optional<UserEntity> possibleUser = userRepository.findUserEntityById(userId);

        if (possibleUser.isPresent()) {
            UserEntity user = possibleUser.get();
            return BankAccountDto.builder()
                .accountNumber(user.getAccount().getAccountNumber())
                .balance(user.getAccount().getBalance())
                .build();
        }

        throw new NotFoundAccountException(
            String.format(
                "Account with id %s not found",
                userId)
        );
    }

    @Override
    public void createPIN(UUID accountNumber, String pin) {
        Optional<BankAccountEntity> possibleAccount = accountRepository.
            findBankAccountEntityByAccountNumber(accountNumber);

        possibleAccount.ifPresentOrElse((account) -> {
                account.setPinCode(pin);
                accountRepository.save(account);
            }, () -> {
                throw new NotFoundAccountException("Account with id %s not found".formatted(accountNumber.toString()));
            }
        );
    }
}
