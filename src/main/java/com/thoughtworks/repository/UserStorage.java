package com.thoughtworks.repository;

import com.thoughtworks.domain.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<Integer, User> USERS = new HashMap<>();

    public static void clear() {
        USERS.clear();
    }

    public static void putUsers(User...users) {
        Arrays.stream(users).forEach(user -> USERS.put(user.getId(), user));
    }

    public static Collection<User> getUsers() {
        return USERS.values();
    }

    public static void putUser(User user) {
        USERS.put(user.getId(), user);
    }

    public static User getUserById(int id) {
        return USERS.get(id);
    }

    public static void deleteUserById(int id) {
        USERS.remove(id);
    }
}
