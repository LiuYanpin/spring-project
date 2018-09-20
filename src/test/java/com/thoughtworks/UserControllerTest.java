package com.thoughtworks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.controller.UserController;
import com.thoughtworks.domain.User;
import com.thoughtworks.repository.UserStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    private MockMvc mockMvc;
    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(controller()).build();
        UserStorage.clear();
    }

    private Object controller() {
        return new UserController();
    }

    @Test
    void should_return_users() throws Exception{
        UserStorage.putUsers(new User(1, "sun ming"), new User(2, "liu yanping"));
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_post_user_to_storage_and_get_correct_size() throws Exception{
        UserStorage.putUsers(new User(1, "liu yanping"));
        int previousSize = UserStorage.getUsers().size();
        User newUser = new User(2, "sun ming");
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("sun ming"));
        int afterSize = UserStorage.getUsers().size();
        assertThat(afterSize).isEqualTo(previousSize + 1);
    }

    @AfterEach
    void teardown() {
        UserStorage.clear();
    }
}
