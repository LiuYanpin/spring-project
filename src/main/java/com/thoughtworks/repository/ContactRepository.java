package com.thoughtworks.repository;

import com.thoughtworks.domain.Contact;

import java.util.List;

public interface ContactRepository {
    void putContact(Contact contact);

    List<Contact> getContacts(List<Integer> userContacts);

    Contact updateContact(Contact contact);

    void deleteContact(int contactid);

    Contact getContactByIdAndName(List<Integer> contactids, String contactname);
}
