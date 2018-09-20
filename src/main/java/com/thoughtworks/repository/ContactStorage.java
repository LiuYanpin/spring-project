package com.thoughtworks.repository;

import com.thoughtworks.domain.Contact;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ContactStorage {
    private final static Map<Integer, Contact> CONTACTS = new HashMap<>();

    public static void putContact(Contact contact) {
        CONTACTS.put(contact.getId(), contact);
    }

    public static Collection<Contact> getContacts() {
        return CONTACTS.values();
    }
}
