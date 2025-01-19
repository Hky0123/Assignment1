package com.assignment.assignment.daoImplementationTest;

import com.assignment.assignment.dao.daoImplement.UserDetailServiceImpl;
import com.assignment.assignment.entity.User;
import com.assignment.assignment.repository.UserDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserDetailServiceImplTest {

    @Mock
    private UserDetailRepository userDetailRepository;

    @InjectMocks
    UserDetailServiceImpl userDetailServiceImpl;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserName("gnn");
        user.setUserAccountId("u131");
    }

    @Test
    @Order(1)
    public void getUserByIdTestFound() {
        when(userDetailRepository.findById("u131")).thenReturn(Optional.of(user));

        User usr = userDetailServiceImpl.getUserById("u131");

        Assertions.assertEquals(usr, user);
        verify(userDetailRepository, times(1)).findById("u131");

    }

    @Test
    @Order(2)
    public void getUserByIdTestNotFound() {
        when(userDetailRepository.findById("u132")).thenReturn(Optional.empty());

        User usr = userDetailServiceImpl.getUserById("u132");

        Assertions.assertNull(usr);
        verify(userDetailRepository, times(1)).findById("u132");

    }

    @Test
    @Order(3)
    public void createUserTestSuccess() {
        when(userDetailRepository.findById("u131")).thenReturn(Optional.empty());
        when(userDetailRepository.save(user)).thenReturn(user);

        ResponseEntity<?> response = userDetailServiceImpl.createUser(user);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        verify(userDetailRepository, times(1)).findById("u131");
        verify(userDetailRepository, times(1)).save(user);

    }

    @Test
    @Order(4)
    public void createUserTestFailed(){
        when(userDetailRepository.findById("u131")).thenReturn(Optional.of(user));

        ResponseEntity<?>response= userDetailServiceImpl.createUser(user);

        Assertions.assertEquals(response.getStatusCode(),HttpStatus.ALREADY_REPORTED);
        verify(userDetailRepository,times(1)).findById("u131");
        verify(userDetailRepository,never()).save(user);
    }

}