package com.lambdaschool.friendfinder.controllers;

import com.lambdaschool.friendfinder.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAllUsers() throws Exception {
    }

    @Test
    void getUser() throws Exception {
    }

    @Test
    void getCurrentUserName() throws Exception {
    }

    @Test
    void getCurrentUser() throws Exception {
    }

    @Test
    void addNewUser() throws Exception {
    }

    @Test
    void updateUser() throws Exception {
    }

    @Test
    void deleteUserById() throws Exception {
    }
}