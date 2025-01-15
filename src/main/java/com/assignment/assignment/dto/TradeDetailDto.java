package com.assignment.assignment.dto;

import lombok.Data;

@Data
public class TradeDetailDto {
    private Long tradeId;
    private  String userAccountId;
    private Long stockQuantity;
    private String stockId;
    private Long stockPurchasedPrice;
    private String tradeType;
}
