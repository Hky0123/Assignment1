package com.assignment.assignment.repository;

import com.assignment.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<User,String> {
}
