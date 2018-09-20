package com.thoughtworks.repository;

import com.thoughtworks.domain.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> getUsers();

}
