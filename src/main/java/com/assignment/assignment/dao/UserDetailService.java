package com.assignment.assignment.dao;

import com.assignment.assignment.dto.UserDto;
import com.assignment.assignment.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserDetailService {
    User getUserById(String userAccountId);
    ResponseEntity<?>createUser(User user);
}
