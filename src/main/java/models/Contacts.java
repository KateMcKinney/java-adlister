package models;

import java.util.List;

// INTERFACE FOR CONTACT BEAN
public interface Contacts {
    // List all contacts (List<Models.Contact>)
    List<Contact> getContacts();

    // Method to make new contact (will assign new id if we don't)
    long saveContact(Contact contact);

    // Method to delete contact
    void deleteContact(long id);

    // Get contact by id
    Contact getContactById(long id);
}
