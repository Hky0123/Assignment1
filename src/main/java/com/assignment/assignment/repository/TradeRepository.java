package com.assignment.assignment.repository;

import com.assignment.assignment.entity.TradeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<TradeDetails,String> {
    List<TradeDetails>findByUserAccountId(String userAccountId);
}
