package com.thoughtworks.repository;

import com.thoughtworks.domain.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> getUsers();

    void putUser(User user);

    User updateUser(int id, User user);

    void deleteUserById(int id);

    void putContact(int userid, int contact);
}
