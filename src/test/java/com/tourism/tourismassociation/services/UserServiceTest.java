package com.tourism.tourismassociation.services;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import com.tourism.tourismassociation.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void getAllUsers(){
        //arrange
        User mockUser = new User("mahir@gmail.com","huhuh452323");
        User mockUser2 = new User("adem@gmail.com","huhuh452323");
        doReturn(Arrays.asList(mockUser,mockUser2)).when(userRepository).findAll();

        //act
        List<User> userList = userService.findAll();

        //assert
        Assertions.assertEquals(2, userList.size(), "FindAll should return 2 users");


    }

}
