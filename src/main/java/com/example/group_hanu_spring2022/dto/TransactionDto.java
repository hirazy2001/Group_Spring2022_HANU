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
public class TransactionDto {

    private String pinCode;
    private Long from_account;
    private Long to_account;
    private BigDecimal amount;
    private TransactionType type;
    private String purpose;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "pinCode='" + pinCode + '\'' +
                ", from_account=" + from_account +
                ", to_account=" + to_account +
                ", amount=" + amount +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
