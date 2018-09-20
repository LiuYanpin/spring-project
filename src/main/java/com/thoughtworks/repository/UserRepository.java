package com.thoughtworks.repository;

import com.thoughtworks.domain.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {
    Collection<User> getUsers();

    void putUser(User user);

    User updateUser(int id, User user);

    void deleteUserById(int id);

    void putContact(int userid, int contact);

    List<Integer> getContactsByUserId(int userid);

    void deleteContactByUserId(int userid, int contactid);
}
