import java.util.ArrayList;
import java.util.List;

public class ContactListDAO implements Contacts{

    //List variable to hold the contacts (cast to ArrayList)
    private List<Contact> contacts = new ArrayList<>();

        @Override
        public List<Contact> getContacts() {
            return contacts;
        }

        @Override
        public long saveContact(Contact contact) {
            // if this is first contact, +1 cause 0 index
            if (contact.getId() == 0){
                contact.setId(contacts.size() + 1);
                contacts.add(contact);
            }
            else {
                contacts.set((int) (contact.getId() - 1), contact);
            }
            return contact.getId();
        }

        @Override
        public void deleteContact(long id) {
            contacts.remove((int) id - 1);
        }

        @Override
        public Contact getContactById(long id) {
            return contacts.get((int) id - 1);
        }
}
