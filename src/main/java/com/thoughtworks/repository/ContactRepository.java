package com.thoughtworks.repository;

import com.thoughtworks.domain.Contact;

import java.util.List;

public interface ContactRepository {
    void putContact(Contact contact);

    List<Contact> getContacts(List<Integer> userContacts);
}
