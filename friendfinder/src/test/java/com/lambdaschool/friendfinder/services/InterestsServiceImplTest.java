package com.lambdaschool.friendfinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lambdaschool.friendfinder.FriendFinderApplication;
import com.lambdaschool.friendfinder.repository.InterestsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendFinderApplication.class)
class InterestsServiceImplTest {
    @Autowired
    private InterestsService interestsService;

    @Autowired
    private InterestsRepository interestsrepo;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws Exception {
    }

    @Test
    void findInterestsById() throws Exception {
    }

    @Test
    void delete() throws Exception {
    }

    @Test
    void save() throws Exception {
    }

    @Test
    void update() throws Exception {
    }
}