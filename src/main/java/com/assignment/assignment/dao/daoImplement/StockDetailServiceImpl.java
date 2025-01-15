package com.assignment.assignment.dao.daoImplement;

import com.assignment.assignment.entity.StockDetail;
import com.assignment.assignment.dao.StockDetailService;
import com.assignment.assignment.repository.StockDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockDetailServiceImpl  implements StockDetailService {

   @Autowired
   StockDetailRepository stockDetailRepository;

    public StockDetailServiceImpl(StockDetailRepository stockDetailRepository){
        super();
        this.stockDetailRepository=stockDetailRepository;
    }

    @Override
   public List<StockDetail>getAllTheStocks(){
      return stockDetailRepository.findAll();
    }

    @Override
    public StockDetail getStockDetailById(String stockId){
        return stockDetailRepository.findById(stockId).orElseThrow(()-> new RuntimeException("StockId is not exist"));
    }

    @Override

    public ResponseEntity<?> createStockDetail(StockDetail stockDetail){
        return stockDetailRepository.findById(stockDetail.getStockId())
                .map(detail->new ResponseEntity<>(HttpStatus.ALREADY_REPORTED))
                .orElseGet(()->{
                    stockDetailRepository.save(stockDetail);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }
}
