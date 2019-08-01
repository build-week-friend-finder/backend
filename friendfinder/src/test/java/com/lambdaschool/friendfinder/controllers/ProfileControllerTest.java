package com.lambdaschool.friendfinder.controllers;

import com.lambdaschool.friendfinder.services.ProfileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProfileController.class, secure = false)
class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileService profileService;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getListOfAllProfiles() throws Exception {
    }

    @Test
    void getListOfProfiles() throws Exception {
    }

    @Test
    void getProfileByUserId() throws Exception {
    }

    @Test
    void getListOfMatchesForUserByUserId() throws Exception {
    }

    @Test
    void createProfile() throws Exception {
    }

    @Test
    void updateProfileByUserId() throws Exception {
    }
}