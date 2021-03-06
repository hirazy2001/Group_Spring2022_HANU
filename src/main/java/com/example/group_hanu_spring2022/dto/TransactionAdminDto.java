package com.example.group_hanu_spring2022.dto;

import com.example.group_hanu_spring2022.model.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionAdminDto {

    private Long id;
    private Long from_account;
    private Long to_account;
    private BigDecimal amount;
    private String purpose;
    private TransactionType type;
    private LocalDateTime createdAt;

}
