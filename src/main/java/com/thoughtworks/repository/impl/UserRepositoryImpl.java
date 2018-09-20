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

    @Override
    public void putUser(User user) {
        UserStorage.putUser(user);
    }

    @Override
    public User updateUser(int id, User user) {
        UserStorage.putUser(user);
        return user;
    }

    @Override
    public void putContact(int userid, int contactid) {
        UserStorage.putContactByUserId(userid, contactid);
    }

    @Override
    public void deleteUserById(int id) {
        UserStorage.deleteUserById(id);
    }
}
