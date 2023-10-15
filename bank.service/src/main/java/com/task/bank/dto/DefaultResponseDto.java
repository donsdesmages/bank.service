package com.task.bank.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class DefaultResponseDto {
    private LocalDateTime date;
    private String body;
    private String errorMessage;
}
