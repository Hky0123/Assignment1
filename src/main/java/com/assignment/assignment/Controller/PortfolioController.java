package com.assignment.assignment.Controller;

import com.assignment.assignment.dao.PortfolioService;
import com.assignment.assignment.dto.PortofolioResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{userAccountId}")
    public ResponseEntity<PortofolioResponseDto> getPortfolioByUserId(@PathVariable String userAccountId){

            PortofolioResponseDto response = portfolioService.getPortfolioByUserId(userAccountId);
        return ResponseEntity.ok(response);

    }
}
