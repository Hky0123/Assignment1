package com.assignment.assignment.Controller;

import com.assignment.assignment.entity.StockDetail;
import com.assignment.assignment.dao.StockDetailService;

import com.assignment.assignment.dto.StockDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/stockDetail")

public class StockDetailController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private StockDetailService stockDetailService;


    @GetMapping("/{stockId}")
    public ResponseEntity<?> getStockDetailById(@PathVariable String stockId){
        try{
                StockDetail stockDetail=stockDetailService.getStockDetailById(stockId);
                StockDetailDto stockDetailDto=modelMapper.map(stockDetail, StockDetailDto.class);
                return new ResponseEntity<>(stockDetailDto,HttpStatus.FOUND);
        }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid id");
        }
    }

    @PostMapping
    public ResponseEntity<?>createStockDetailEntry(@RequestBody StockDetailDto stockDetailDto){
        try{
            StockDetail stockDetail = modelMapper.map(stockDetailDto, StockDetail.class);
            stockDetailService.createStockDetail(stockDetail);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
