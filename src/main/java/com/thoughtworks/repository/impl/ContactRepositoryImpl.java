package com.thoughtworks.repository.impl;

import com.thoughtworks.domain.Contact;
import com.thoughtworks.repository.ContactRepository;
import com.thoughtworks.repository.ContactStorage;

public class ContactRepositoryImpl implements ContactRepository {
    @Override
    public void putContact(Contact contact) {
        ContactStorage.putContact(contact);
    }
}
