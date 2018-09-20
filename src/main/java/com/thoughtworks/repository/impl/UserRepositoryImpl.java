package com.thoughtworks.repository.impl;

import com.thoughtworks.domain.User;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.repository.UserStorage;

import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Collection<User> getUsers() {
        return UserStorage.getUsers();
    }
}
