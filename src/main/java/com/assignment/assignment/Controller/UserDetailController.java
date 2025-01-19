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

    @GetMapping("/{userAccountId}")
    public ResponseEntity<?> getUserDetailById(@PathVariable String userAccountId){

       try {
           User user = userDetailService.getUserById(userAccountId);
           UserDto userDto=new UserDto(user.getUserName(),user.getUserAccountId());
           return new ResponseEntity<>(userDto, HttpStatus.FOUND);
       }catch(Exception e){
           return new ResponseEntity<>("Not exist in Db",HttpStatus.NOT_FOUND);
       }

    }


    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) {
        try {
            User existingUser = userDetailService.getUserById(userDto.getUserAccountId());
            if (existingUser != null) {
                return new ResponseEntity<>("Already exists",HttpStatus.ALREADY_REPORTED);

            }else {
                User user = new User(userDto.getUserName(), userDto.getUserAccountId());
                ResponseEntity<?> response = userDetailService.createUser(user);
                return new ResponseEntity<>(response.getBody(), response.getStatusCode());
            }
        }catch(Exception e ){
                return new ResponseEntity<>("error",HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }




}
