package com.assignment.assignment.Controller;

import com.assignment.assignment.dao.TradeDetailService;
import com.assignment.assignment.dto.TradeDetailDto;
import com.assignment.assignment.entity.TradeDetails;
import org.aspectj.asm.IModelFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private TradeDetailService tradeDetailService;

    @GetMapping
    public ResponseEntity<String>userTradeController(@RequestBody TradeDetailDto tradeDetailDto){

        if(tradeDetailDto.getUserAccountId()==null || tradeDetailDto.getUserAccountId().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid account Id");
        }
        if(tradeDetailDto.getTradeType()==null || tradeDetailDto.getTradeType().isEmpty()
                ||(!tradeDetailDto.getTradeType().equalsIgnoreCase("Buy") && !tradeDetailDto.getTradeType().equalsIgnoreCase("sell"))){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Given Trade type is invalid");
        }
        if(tradeDetailDto.getStockQuantity()<=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid stock quantity");
        }

        if(tradeDetailDto.getStockId()==null || tradeDetailDto.getStockId().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid stockId");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Trade request is valid");
    }

    @PostMapping

    public ResponseEntity<?> createNewTrade(@RequestBody TradeDetailDto tradeDetailDto){

        TradeDetails  tradeDetails = modelMapper.map(tradeDetailDto,TradeDetails.class);

        try{
            tradeDetailService.getTradeDetailsById(tradeDetails.getTradeId());
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }catch(Exception e){
            tradeDetailService.saveTradeDetail(tradeDetails);
            return new ResponseEntity<>(tradeDetails,HttpStatus.CREATED);
        }


    }
}
