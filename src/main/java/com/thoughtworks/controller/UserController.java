package com.thoughtworks.controller;

import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.repository.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserRepository userRepositor = new UserRepositoryImpl();
    @GetMapping("/api/users")
    public ResponseEntity getUsers() {
        return new ResponseEntity(userRepositor.getUsers(), HttpStatus.OK);
    }

}
