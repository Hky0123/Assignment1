package com.assignment.assignment.dao.daoImplement;

import com.assignment.assignment.dao.PortfolioService;
import com.assignment.assignment.dto.HoldingResponse;
import com.assignment.assignment.dto.PortofolioResponseDto;
import com.assignment.assignment.entity.StockDetail;
import com.assignment.assignment.entity.TradeDetails;
import com.assignment.assignment.repository.StockDetailRepository;
import com.assignment.assignment.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service


public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private StockDetailRepository stockDetailRepository;

    @Override
    public PortofolioResponseDto getPortfolioByUserId(String userAccountId){
        List<TradeDetails> trades=tradeRepository.findByUserAccountId(userAccountId);
        List<HoldingResponse>holdingList=new ArrayList<>();
        long totalBuyPrice=0;
        long totalCurrentValue=0;
        long totalPL=0;

        for(TradeDetails tradeDetails:trades){
            StockDetail stockDetail=stockDetailRepository.findById(tradeDetails.getStockId()).orElse(null);
            if(stockDetail!=null){
               HoldingResponse holdingResponse=new HoldingResponse();
               holdingResponse.setStockId(stockDetail.getStockId());
               holdingResponse.setStockName(stockDetail.getStockName());
               holdingResponse.setCurrentPrice(stockDetail.getStockCurrentPrice());
               holdingResponse.setQuantity(tradeDetails.getStockQuantity());
               holdingResponse.setBuyPrice(tradeDetails.getStockPurchasedPrice());

               long totalGainLoss=((stockDetail.getStockCurrentPrice())-(tradeDetails.getStockPurchasedPrice()))*(tradeDetails.getStockQuantity());
               holdingResponse.setGainLoss(totalGainLoss);

               holdingList.add(holdingResponse);

               totalBuyPrice+=(tradeDetails.getStockPurchasedPrice())*(tradeDetails.getStockQuantity());
               totalCurrentValue+=(stockDetail.getStockCurrentPrice())*(tradeDetails.getStockQuantity());
               totalPL+=totalGainLoss;


            }
        }

        PortofolioResponseDto portofolioResponseDto=new PortofolioResponseDto();
        portofolioResponseDto.setHoldings(holdingList);
        portofolioResponseDto.setTotalPL(totalPL);
        portofolioResponseDto.setTotalBuyPrice(totalBuyPrice);
        portofolioResponseDto.setTotalPortfolioHolding(totalCurrentValue);
        portofolioResponseDto.setPLPercentage((totalPL*100)/totalBuyPrice);

      return  portofolioResponseDto;
    }
}
