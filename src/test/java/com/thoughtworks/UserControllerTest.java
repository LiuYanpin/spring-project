package com.thoughtworks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.controller.UserController;
import com.thoughtworks.domain.Contact;
import com.thoughtworks.domain.User;
import com.thoughtworks.repository.ContactStorage;
import com.thoughtworks.repository.UserStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    private MockMvc mockMvc;
    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(controller()).build();
        UserStorage.clear();
        ContactStorage.clear();
    }

    private Object controller() {
        return new UserController();
    }

    @Test
    void should_get_users() throws Exception{
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

    @Test
    void should_put_user_correctly() throws Exception{
        UserStorage.putUser(new User(1, "liu yanping"));
        User updateUser = new User(1, "liu ping");
        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(updateUser)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("liu ping"))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void should_delete_user() throws Exception{
        UserStorage.putUser(new User(1, "liu yanping"));
        mockMvc.perform(delete("/api/users/1"))
               .andExpect(status().isNoContent());

        assertThat(UserStorage.getUsers().size()).isEqualTo(0);
        assertThat(UserStorage.getUserById(1)).isNull();
    }

    @Test
    void should_create_contact_of_user() throws Exception{
        UserStorage.putUser(new User(5, "sjyuan"));
        Contact contact = new Contact(1, "dq", "male", "13001211212", 16);
        mockMvc.perform(post("/api/users/5/contacts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(contact)))
                .andExpect(status().isCreated());
        assertThat(ContactStorage.getContacts().size()).isEqualTo(1);
    }

    @Test
    void should_get_contacts_of_user() throws Exception{
        User user = new User(5, "sjyuan");
        UserStorage.putUser(user);
        ContactStorage.putContact(new Contact(1, "dq", "male", "13001211212", 16));
        ContactStorage.putContact(new Contact(2, "liuxia", "male", "13212312312", 18));
        user.putContact(1);
        user.putContact(2);
        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("dq"))
                .andExpect(jsonPath("$.[0].gender").value("male"))
                .andExpect(jsonPath("$.[0].telephone").value("13001211212"))
                .andExpect(jsonPath("$.[0].age").value(16))
                .andExpect(jsonPath("$.[1].name").value("liuxia"))
                .andExpect(jsonPath("$.[1].gender").value("male"))
                .andExpect(jsonPath("$.[1].telephone").value("13212312312"))
                .andExpect(jsonPath("$.[1].age").value(18));
        assertThat(ContactStorage.getContacts().size()).isEqualTo(2);
    }

    @Test
    void should_put_contact_of_user() throws Exception{
        User user = new User(5, "sjyuan");
        UserStorage.putUser(user);
        ContactStorage.putContact(new Contact(1, "dq", "male", "13001211212", 16));
        ContactStorage.putContact(new Contact(2, "liuxia", "male", "13212312312", 18));
        user.putContact(1);
        user.putContact(2);
        Contact updateContact = new Contact(2, "liuxia", "male", "18978897866", 20);

        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(put("/api/users/5/contacts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(updateContact)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("liuxia"))
                .andExpect(jsonPath("$.telephone").value("18978897866"))
                .andExpect(jsonPath("$.age").value(20));

        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_delete_contact_of_user() throws Exception{
        User user = new User(5, "sjyuan");
        UserStorage.putUser(user);
        ContactStorage.putContact(new Contact(1, "dq", "male", "13001211212", 16));
        ContactStorage.putContact(new Contact(2, "liuxia", "male", "13212312312", 18));
        user.putContact(1);
        user.putContact(2);
        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(delete("/api/users/5/contacts/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void should_query_contact_by_name_of_user() throws Exception{
        User user = new User(5, "sjyuan");
        UserStorage.putUser(user);
        ContactStorage.putContact(new Contact(1, "yang kaiguang", "male", "13001211212", 16));
        user.putContact(1);
        mockMvc.perform(get("/api/users/5/contacts/yang kaiguang"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("yang kaiguang"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.telephone").value("13001211212"))
                .andExpect(jsonPath("$.age").value(16));

    }

    @AfterEach
    void teardown() {
        UserStorage.clear();
        ContactStorage.clear();
    }
}
