package com.lambdaschool.friendfinder.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.friendfinder.models.User;
import com.lambdaschool.friendfinder.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> userList;

    @BeforeEach
    void setUp() throws Exception {
        userList = new ArrayList<>();

        User u1 = new User("test1", "test");
        User u2 = new User("test2", "test");
        User u3 = new User("test3", "test");
        User u4 = new User("test4", "test");
        u1.setUserid(1);
        u2.setUserid(2);
        u3.setUserid(3);
        u4.setUserid(4);

        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAllUsers() throws Exception {
        String apiUrl = "/users/users";

        Mockito.when(userService.findAll()).thenReturn(userList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        // The following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    void getUser() throws Exception {
        String apiUrl = "/users/user/4";

        Mockito.when(userService.findUserById(4)).thenReturn(userList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn(); // this could throw an exception
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(1));

        assertEquals("Rest API Returns List", er, tr);
    }

    // Don't know how to test Authentication yet
//    @Test
//    void getCurrentUserName() throws Exception {
//    }
//
//    @Test
//    void getCurrentUser() throws Exception {
//    }

    @Test
    void addNewUser() throws Exception {
        String apiUrl = "/users/user";

        User u1 = new User("test", "test");
        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u1);

        Mockito.when(userService.save(any(User.class))).thenReturn(u1);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(userString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateUser() throws Exception {
        String apiUrl = "/users/user/{userid}";

        User u1 = new User("trevor", "trevor");
        u1.setUserid(10);

        Mockito.when(userService.update(u1, 10L)).thenReturn(u1);
        ObjectMapper mapper = new ObjectMapper();
        String restaurantString = mapper.writeValueAsString(u1);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 10L)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(restaurantString);

        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteUserById() throws Exception {
        String apiUrl = "/users/user/{userid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "4").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}