package com.assignment.assignment.Controller;

import com.assignment.assignment.dao.StockDetailService;
import com.assignment.assignment.dto.HoldingResponse;
import com.assignment.assignment.entity.StockDetail;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/updateStockTable")
public class UpdateStockTableFromCsvController {

    @Autowired
    private StockDetailService stockDetailService;

    private String fileUrl="/Users/harikyad/Downloads/Stock Details - Sheet1.csv";

    @PostMapping
    ResponseEntity<?>updateStockTableFromCsvFile(String fileURL)throws Exception{

        try(CSVReader csvReader=new CSVReader(new FileReader(fileURL))){
             String[] header= csvReader.readNext();
             Map<String,Integer> headerIndex=new HashMap<>();

             for(int i=0;i<header.length;i++){
                 headerIndex.put(header[i].trim(),i);
             }

            String[] line;
            while((line=csvReader.readNext())!=null){
                 StockDetail stk=new StockDetail();
                 stk.setStockCurrentPrice(Long.parseLong(line[headerIndex.get("stockCurrentPrice")]));
                 stk.setStockId(line[headerIndex.get("stockId")]);
                 stk.setStockName(line[headerIndex.get("stockName")]);
                 stockDetailService.createStockDetail(stk);
             }

        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }



}
