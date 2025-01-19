package com.assignment.assignment.dao.daoImplement;

import com.assignment.assignment.dao.TradeDetailService;
import com.assignment.assignment.entity.TradeDetails;
import com.assignment.assignment.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TradeDetailServiceImpl implements TradeDetailService {

    @Autowired
    TradeRepository tradeRepository;

    public TradeDetailServiceImpl(TradeRepository tradeRepository){
        super();
        this.tradeRepository=tradeRepository;
    }

    @Override
   public  TradeDetails getTradeDetailsById(Long tradeId){
        return tradeRepository.findById(tradeId).orElse(null);
    }

    @Override
    public ResponseEntity<?>saveTradeDetail(TradeDetails tradeDetails){

        return tradeRepository.findById(tradeDetails.getTradeId())
                .map(deatil->new ResponseEntity<>(HttpStatus.ALREADY_REPORTED))
                .orElseGet(()->{
                    tradeRepository.save(tradeDetails);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }
}
