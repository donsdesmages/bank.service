package com.task.bank.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class BankAccountDto {
    @NotBlank
    private UUID accountNumber;
    @NotBlank
    private BigDecimal balance;
}
