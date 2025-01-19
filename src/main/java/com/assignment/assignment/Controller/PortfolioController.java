package com.assignment.assignment.Controller;

import com.assignment.assignment.dao.PortfolioService;
import com.assignment.assignment.dao.UserDetailService;
import com.assignment.assignment.dto.PortofolioResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/{userAccountId}")
    public ResponseEntity<?> getPortfolioByUserId(@PathVariable String userAccountId){
           if(userDetailService.getUserById(userAccountId)==null){
               return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user Account Id");
           }

            PortofolioResponseDto response = portfolioService.getPortfolioByUserId(userAccountId);
        return ResponseEntity.ok(response);

    }
}
