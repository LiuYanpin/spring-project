package com.thoughtworks.controller;

import com.thoughtworks.domain.Contact;
import com.thoughtworks.domain.User;
import com.thoughtworks.repository.ContactRepository;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.repository.impl.ContactRepositoryImpl;
import com.thoughtworks.repository.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserRepository userRepository = new UserRepositoryImpl();
    private ContactRepository contactRepository = new ContactRepositoryImpl();
    @GetMapping("/api/users")
    public ResponseEntity queryUsers() {
        return new ResponseEntity(userRepository.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity createUser(@RequestBody User user) {
        userRepository.putUser(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) {
        return new ResponseEntity(userRepository.updateUser(id, user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteUserById(id);
    }

    @PostMapping("/api/users/{userid}/contacts")
    public ResponseEntity createContactByUserId(@PathVariable int userid, @RequestBody Contact contact) {
        userRepository.putContact(userid, contact.getId());
        contactRepository.putContact(contact);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
