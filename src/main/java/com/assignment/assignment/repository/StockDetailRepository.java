package com.assignment.assignment.repository;

import com.assignment.assignment.entity.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDetailRepository extends JpaRepository<StockDetail,String> {
}
