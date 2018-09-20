package com.thoughtworks.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Integer> contacts = new ArrayList<>();
    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void putContact(int contactid) {
        contacts.add(contactid);
    }

    public List<Integer> getContacts() {
        return contacts;
    }

    public void deleteContact(int contactid) {
        contacts.remove(contactid);
    }
}
