package com.assignment.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="stockDetails")
@AllArgsConstructor
@NoArgsConstructor
public class StockDetail {
    @Id
    private String stockId;
    private String stockName;
    private Long   stockCurrentPrice;

}
