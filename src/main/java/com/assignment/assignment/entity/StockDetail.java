package com.assignment.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="stockDetails")
public class StockDetail {
    @Id
    private String stockId;
    private String stockName;
    private Long   stockCurrentPrice;

}
