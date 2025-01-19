package com.assignment.assignment.repository;

import com.assignment.assignment.entity.TradeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<TradeDetails,Long> {
    List<TradeDetails>findByUserAccountId(String userAccountId);
}
