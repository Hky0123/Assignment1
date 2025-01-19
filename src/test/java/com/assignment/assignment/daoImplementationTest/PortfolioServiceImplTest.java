package com.assignment.assignment.daoImplementationTest;

import com.assignment.assignment.dao.daoImplement.PortfolioServiceImpl;
import com.assignment.assignment.dto.HoldingResponse;
import com.assignment.assignment.dto.PortofolioResponseDto;
import com.assignment.assignment.entity.StockDetail;
import com.assignment.assignment.entity.TradeDetails;
import com.assignment.assignment.repository.StockDetailRepository;
import com.assignment.assignment.repository.TradeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PortfolioServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private StockDetailRepository stockDetailRepository;

    @InjectMocks
    private PortfolioServiceImpl portfolioService;

    private PortofolioResponseDto portfolioResponseDto; // Corrected typo in variable name
    private HoldingResponse holdingResponse;


    @BeforeEach
    void setUp() {

        holdingResponse = new HoldingResponse();
        holdingResponse.setStockName("growW");
        holdingResponse.setStockId("s121");
        holdingResponse.setBuyPrice(100L);
        holdingResponse.setGainLoss(1000L);
        holdingResponse.setCurrentPrice(2L);
        holdingResponse.setQuantity(20L);

        List<HoldingResponse> holdings = new ArrayList<>();
        holdings.add(holdingResponse);

        portfolioResponseDto = new PortofolioResponseDto();
        portfolioResponseDto.setHoldings(holdings);
        portfolioResponseDto.setTotalBuyPrice(400L);
        portfolioResponseDto.setTotalPortfolioHolding(2340L);
        portfolioResponseDto.setPLPercentage(23L);
        portfolioResponseDto.setTotalPL(400L);
    }

    @Test
    @Order(1)
    public void testGetPortfolioByIdSuccess() {
        when(tradeRepository.findByUserAccountId("u111"))
                .thenReturn(List.of(new TradeDetails(1L, "u111", 20L, "s121", 100L)));

        when(stockDetailRepository.findById("s121"))
                .thenReturn(Optional.of(new StockDetail("s121","growW",2L)));

        PortofolioResponseDto prt = portfolioService.getPortfolioByUserId("u111");

        Assertions.assertNotNull(prt);
        Assertions.assertEquals(1, prt.getHoldings().size());
        Assertions.assertEquals("growW", prt.getHoldings().get(0).getStockName());

        verify(tradeRepository, times(1)).findByUserAccountId("u111");
        verify(stockDetailRepository, times(1)).findById("s121");
    }

    @Test
    @Order(2)
    public void testGetPortfolioByIdFailed(){
        when(tradeRepository.findByUserAccountId("u111"))
                 .thenReturn(List.of());

        when(stockDetailRepository.findById("s121"))
                .thenReturn(Optional.of(new StockDetail("s121","growW",2L)));

        PortofolioResponseDto prt = portfolioService.getPortfolioByUserId("u111");

        Assertions.assertNull(prt);

        verify(tradeRepository, times(1)).findByUserAccountId("u111");
        verify(stockDetailRepository, times(0)).findById("s121");
    }
}
