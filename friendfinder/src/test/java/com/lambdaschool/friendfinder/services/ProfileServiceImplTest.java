package com.lambdaschool.friendfinder.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lambdaschool.friendfinder.FriendFinderApplication;
import com.lambdaschool.friendfinder.models.Interests;
import com.lambdaschool.friendfinder.models.Profile;
import com.lambdaschool.friendfinder.models.User;
import com.lambdaschool.friendfinder.repository.ProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendFinderApplication.class)
class ProfileServiceImplTest {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profilerepo;

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
        assertEquals(1, profileService.findAll(Pageable.unpaged()).size());
    }

    @Test
    void findProfileById() throws Exception {
        assertEquals("Jack", profileService.findProfileById(13).getName());
    }

    @Test
    void save() throws Exception {
        ArrayList<Interests> arrayI = new ArrayList<>();
        arrayI.add(new Interests("Basketball"));
        arrayI.add(new Interests("Soccer"));
        arrayI.add(new Interests("Football"));
        arrayI.add(new Interests("Lacrosse"));

        Profile p1 = new Profile("Beth", "F", "This is a test", arrayI);

        Profile newProfile = profileService.save(p1);

        assertNotNull(newProfile);

        assertEquals(newProfile.getDescription(), profileService.findProfileByProfileId(19).getDescription());
    }

    // Don't know how to get Authentication header stuff for tests
//    @Test
//    void assign() throws Exception {
//    }
//
//    @Test
//    void update() throws Exception {
//    }
}