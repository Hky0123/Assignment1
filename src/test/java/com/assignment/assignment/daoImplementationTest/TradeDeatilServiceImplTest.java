package com.assignment.assignment.daoImplementationTest;

import com.assignment.assignment.dao.daoImplement.TradeDetailServiceImpl;
import com.assignment.assignment.entity.TradeDetails;
import com.assignment.assignment.repository.TradeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class TradeDeatilServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeDetailServiceImpl tradeDetailService;

    private TradeDetails tradeDetails;

    @BeforeEach
    void setUp(){
        tradeDetails=new TradeDetails();
        tradeDetails.setTradeId(1L);
        tradeDetails.setStockId("s122");
        tradeDetails.setStockQuantity(10L);
        tradeDetails.setUserAccountId("u122");
        tradeDetails.setStockPurchasedPrice(200L);

    }

    @Test
    @Order(1)
    public void getTradeDetailsByIdTestFound(){
        when(tradeRepository.findById(1L)).thenReturn(Optional.of(tradeDetails));

        TradeDetails trade=tradeDetailService.getTradeDetailsById(1L);

        Assertions.assertEquals(trade,tradeDetails);
        Mockito.verify(tradeRepository,times(1)).findById(1L);
    }

    @Test
    @Order(2)
    public void getTradeDetailsByIdTestNotFound(){
        when(tradeRepository.findById(2L)).thenReturn(Optional.empty());

        TradeDetails trade=tradeDetailService.getTradeDetailsById(2L);

        Assertions.assertNull(trade);
        Mockito.verify(tradeRepository,times(1)).findById(2L);
    }

    @Test
    @Order(3)
    public void testSaveTradeDetailSuccess(){
        when(tradeRepository.findById(1L)).thenReturn(Optional.empty());
        when(tradeRepository.save(tradeDetails)).thenReturn(tradeDetails);

        ResponseEntity<?>response=tradeDetailService.saveTradeDetail(tradeDetails);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());

        verify(tradeRepository,times(1)).findById(1L);
        verify(tradeRepository,times(1)).save(tradeDetails);
    }

    @Test
    @Order(4)

    public void testSaveTradeDetailFailed(){
        when(tradeRepository.findById(1L)).thenReturn(Optional.of(tradeDetails));
        when(tradeRepository.save(tradeDetails)).thenReturn(tradeDetails);

        ResponseEntity<?>response=tradeDetailService.saveTradeDetail(tradeDetails);
        Assertions.assertEquals(HttpStatus.ALREADY_REPORTED,response.getStatusCode());

        verify(tradeRepository,times(1)).findById(1L);
        verify(tradeRepository,times(0)).save(tradeDetails);
    }

}
