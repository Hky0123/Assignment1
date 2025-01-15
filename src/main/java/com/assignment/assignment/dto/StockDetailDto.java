package com.assignment.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StockDetailDto {

    private String stockId;
    private String stockName;
    private Long   stockCurrentPrice;
}
