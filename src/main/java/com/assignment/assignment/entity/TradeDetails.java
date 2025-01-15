package com.assignment.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity

public class TradeDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long tradeId;
    private String userAccountId;
    private Long stockQuantity;
    private String stockId;
    private Long stockPurchasedPrice;

}
