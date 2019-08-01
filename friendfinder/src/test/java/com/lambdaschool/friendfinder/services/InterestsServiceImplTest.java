package com.lambdaschool.friendfinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lambdaschool.friendfinder.FriendFinderApplication;
import com.lambdaschool.friendfinder.models.Interests;
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
        assertEquals(8, interestsService.findAll().size());
    }

    @Test
    void findInterestsById() throws Exception {
        assertEquals("Basketball", interestsService.findInterestsById(4).getInterestname());
    }

    @Test
    void delete() throws Exception {
        interestsService.delete(4);
        assertEquals(7, interestsService.findAll().size());
    }

    @Test
    void save() throws Exception {
        Interests i10 = new Interests("Dunking");
        interestsService.save(i10);

        assertEquals(9, interestsService.findAll().size());
    }

    @Test
    void update() throws Exception {
        Interests updateInterests = new Interests("Bowling");
        interestsService.update(updateInterests, 4);

        assertEquals("Bowling", interestsService.findInterestsById(4).getInterestname());
    }
}