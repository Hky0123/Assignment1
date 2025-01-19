package com.assignment.assignment.dao;

import com.assignment.assignment.entity.TradeDetails;
import org.springframework.http.ResponseEntity;

public interface TradeDetailService {

    TradeDetails getTradeDetailsById(Long tradeId);
    ResponseEntity<?>saveTradeDetail(TradeDetails tradeDetails);
}
