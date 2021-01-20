package com.tourism.tourismassociation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.tourismassociation.DTO.UserDTO;
import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ExtendWith(SpringExtension.class)
public class UserControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName("GET /user - Found")
     void getAllUsers() throws Exception {
        //arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"mahir@gmail.com","zhzhzh42732"));
        userList.add(new User(2,"armin@gmail.com","ksksk42732"));
        when(userService.findAll()).thenReturn(userList);

        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());


    }

    @Test
    @DisplayName("GET /user/{id} - Found")
    void successfullyFoundUserById() throws Exception {
        //arrange
        User mockUser = new User(3, "haris.silajdzic@gmail.com", "ztztz493839");
        doReturn(Optional.of(mockUser)).when(userService).findById(3L);

        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}",3 ))

                //Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.email",is("haris.silajdzic@gmail.com")))
                .andExpect(jsonPath("$.passwordHash",is("ztztz493839")));


    }

    @Test
    @DisplayName("POST /user - success")
    void successfullyCreatedUser() throws Exception {
        //arrange
        UserDTO createUser = new UserDTO("mahir@gmail.com", "hghghg35232" );
        when(userService.save(ArgumentMatchers.any(User.class))).thenReturn(createUser.convertToUserEntity());
        ObjectMapper objectMapper = new ObjectMapper();
        String createUserJSON = objectMapper.writeValueAsString(createUser);

        //act
        ResultActions result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserJSON));

        //assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("mahir@gmail.com"))
                .andExpect(jsonPath("$.passwordHash").value("hghghg35232"));



    }




}
