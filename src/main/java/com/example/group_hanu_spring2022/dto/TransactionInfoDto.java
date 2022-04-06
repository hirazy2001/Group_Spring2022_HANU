package com.example.group_hanu_spring2022.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionInfoDto {

    private Long id;

    private Long account;

    private String purpose;

    private BigDecimal amount;

    private boolean isPlus;

    private LocalDateTime createdAt;

}
