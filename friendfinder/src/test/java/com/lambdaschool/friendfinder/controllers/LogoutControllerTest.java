package com.lambdaschool.friendfinder.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LogoutController.class, secure = false)
class LogoutControllerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() {
    }

    @Test
    void logout() throws Exception {

    }
}