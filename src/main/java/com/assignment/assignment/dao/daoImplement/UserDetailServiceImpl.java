package com.assignment.assignment.dao.daoImplement;

import com.assignment.assignment.dao.UserDetailService;
import com.assignment.assignment.entity.User;
import com.assignment.assignment.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailServiceImpl(UserDetailRepository userDetailRepository) {
        super();
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public User getUserById(String userAccountId) {
        return userDetailRepository.findById(userAccountId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
    }

    @Override
    public ResponseEntity<?> createUser(User user) {
        return userDetailRepository.findById(user.getUserAccountId())
                .map(detail -> new ResponseEntity<>(HttpStatus.ALREADY_REPORTED))
                .orElseGet(() -> {
                    userDetailRepository.save(user);
                    return new ResponseEntity<>(user, HttpStatus.CREATED);
                });
    }
}
