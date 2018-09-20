package com.thoughtworks.repository.impl;

import com.thoughtworks.domain.Contact;
import com.thoughtworks.repository.ContactRepository;
import com.thoughtworks.repository.ContactStorage;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public List<Contact> getContacts(List<Integer> userContacts) {
        return ContactStorage.getContactsByUserId(userContacts);
    }

    @Override
    public void putContact(Contact contact) {
        ContactStorage.putContact(contact);
    }

    @Override
    public void deleteContact(int contactid) {
        ContactStorage.deleteContact(contactid);
    }

    @Override
    public Contact updateContact(Contact contact) {
        ContactStorage.putContact(contact);
        return contact;
    }
}
