package com.assignment.assignment.dao;

import com.assignment.assignment.dto.PortofolioResponseDto;


public interface PortfolioService {
      PortofolioResponseDto getPortfolioByUserId(String userAccountId);
}
