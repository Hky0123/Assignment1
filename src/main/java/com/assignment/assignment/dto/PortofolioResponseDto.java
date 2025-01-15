package com.assignment.assignment.dto;

import lombok.Data;

import java.util.List;

@Data
public class PortofolioResponseDto {
    private List<HoldingResponse>holdings;
    private Long totalPortfolioHolding;
    private Long totalBuyPrice;
    private Long totalPL;
    private Long PLPercentage;
}
