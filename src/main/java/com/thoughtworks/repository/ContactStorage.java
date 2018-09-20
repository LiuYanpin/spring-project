package com.thoughtworks.repository;

import com.thoughtworks.domain.Contact;

import java.util.*;
import java.util.stream.Collectors;

public class ContactStorage {
    private final static Map<Integer, Contact> CONTACTS = new LinkedHashMap<>();

    public static void putContact(Contact contact) {
        CONTACTS.put(contact.getId(), contact);
    }

    public static Collection<Contact> getContacts() {
        return CONTACTS.values();
    }

    public static void clear() {
        CONTACTS.clear();
    }

    public static List<Contact> getContactsByUserId(List<Integer> userContacts) {
        return userContacts.stream().map(contactid -> CONTACTS.get(contactid)).collect(Collectors.toList());
    }
}
