package com.assignment.assignment.daoImplementationTest;

import com.assignment.assignment.dao.daoImplement.StockDetailServiceImpl;
import com.assignment.assignment.entity.StockDetail;
import com.assignment.assignment.repository.StockDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class StockDetailServiceImplTest
{
    @Mock
    private StockDetailRepository stockDetailRepository;

    @InjectMocks
    private StockDetailServiceImpl stockDetailService;

    private StockDetail stockDetail;

    @BeforeEach
    void setUp(){
        stockDetail=new StockDetail();
        stockDetail.setStockName("groww");
        stockDetail.setStockId("s122");
        stockDetail.setStockCurrentPrice(120L);
    }

    @Test
    @Order(1)
    public void testGetStockDetailByIdFound(){
        when(stockDetailRepository.findById("s122")).thenReturn(Optional.of(stockDetail));

        StockDetail stk=stockDetailService.getStockDetailById("s122");

        Assertions.assertEquals(stk,stockDetail);
        verify(stockDetailRepository,times(1)).findById("s122");
    }

    @Test
    @Order(2)
    public void testGetStockDetailByIdNotFound(){
        when(stockDetailRepository.findById("s123")).thenThrow(new RuntimeException());

        Assertions.assertThrows(RuntimeException.class,()->{stockDetailService.getStockDetailById("s123");});
        verify(stockDetailRepository,times(1)).findById("s123");
    }

    @Test
    @Order(3)
    public void testCreateStockDetailSuccess(){
        when(stockDetailRepository.findById("s122")).thenReturn(Optional.empty());

        ResponseEntity<?>response=stockDetailService.createStockDetail(stockDetail);

        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());

        verify(stockDetailRepository,times(1)).findById("s122");

    }
    @Test
    @Order(4)
    public void testCreateStockDetailFailed(){
        when(stockDetailRepository.findById("s122")).thenReturn(Optional.of(stockDetail));
        when(stockDetailRepository.save(stockDetail)).thenReturn(stockDetail);

        ResponseEntity<?>response=stockDetailService.createStockDetail(stockDetail);


        Assertions.assertEquals(HttpStatus.ALREADY_REPORTED,response.getStatusCode());

        verify(stockDetailRepository,times(1)).findById("s122");
        verify(stockDetailRepository,times(0)).save(stockDetail);

    }


}
