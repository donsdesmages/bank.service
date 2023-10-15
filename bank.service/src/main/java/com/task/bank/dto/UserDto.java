package com.task.bank.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@RequiredArgsConstructor
public class UserDto {
    @NotBlank
    private final String name;

    @NotBlank
    private final String lastName;

    @Positive
    private final Integer age;
}
