package com.assignment.assignment.dto;

import lombok.Data;

@Data
public class HoldingResponse {
    private String stockName;
    private String stockId;
    private Long quantity;
    private Long buyPrice;
    private Long currentPrice;
    private Long gainLoss;
}
