package com.assignment.assignment.Controller;

import com.assignment.assignment.dao.UserDetailService;
import com.assignment.assignment.dto.UserDto;
import com.assignment.assignment.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{usedAccountId}")
    public ResponseEntity<?> getUserDetailById(@PathVariable String userAccountId){

       try {
           User user = userDetailService.getUserById(userAccountId);
           UserDto userDto = modelMapper.map(user, UserDto.class);
           return new ResponseEntity<>(userDto, HttpStatus.FOUND);
       }catch(Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

    }


    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        try {
            userDetailService.getUserById(user.getUserAccountId());
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } catch (RuntimeException e) {
            userDetailService.createUser(user);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }
    }



}
