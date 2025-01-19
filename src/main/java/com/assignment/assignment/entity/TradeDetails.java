package com.assignment.assignment.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "trade_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TradeDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long tradeId;
    private String userAccountId;
    private Long stockQuantity;
    private String stockId;
    private Long stockPurchasedPrice;

}
