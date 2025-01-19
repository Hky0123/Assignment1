package com.assignment.assignment.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class TradeDetailDto {

    private Long tradeId;
    private  String userAccountId;
    private Long stockQuantity;
    private String stockId;
    private Long stockPurchasedPrice;
    private String tradeType;
}
