package com.thoughtworks.repository;

import com.thoughtworks.domain.Contact;
import com.thoughtworks.domain.User;

import java.util.*;

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

    public static void putContactByUserId(int userid, int contactid) {
        USERS.get(userid).putContact(contactid);
    }

    public static List<Integer> getContactsByUserId(int userid) {
        return USERS.get(userid).getContacts();
    }

    public static void deleteContactByUserId(int userid, int contactid) {
        USERS.get(userid).deleteContact(contactid);
    }
}
