package com.assignment.assignment.dao;

import com.assignment.assignment.entity.StockDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

public interface StockDetailService {

    List<StockDetail>getAllTheStocks();

    StockDetail getStockDetailById(String stockId);


    ResponseEntity<?> createStockDetail(StockDetail stockDetail);


}
