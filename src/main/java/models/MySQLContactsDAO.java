package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLContactsDAO implements Contacts {
    // Set up the DB connection
        private Connection conn;

    // Instantiate models.Config object
        Config config = new Config();

    // Set up DB driver and make connection
        DriverManager.registerDriver(new Driver());

    // Get connection
        this.conn = DriverManager.getConnection(
            config.getUrl(),
            config.getUsername(),
            config.getPassword()
        );

    @Override
    public List<Contact> getContacts() {
        List<Contact> output = new ArrayList<>();

        // Query the SQL DB table for all contacts
        String query = "SELECT * FROM contacts";

        // Take into account the SQL Exception that needs to be handled
        try {
            // Create a statement object
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);

            // Iterate through our result set and add each contact to our "Contact" Bean
            while (rs.next()){
                output.add(
                    new Contact(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")
                    )
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return output; //returns the List<Contact> of all the contacts we imported
    }

    @Override
    public long saveContact(Contact contact) {
        // a Contact object sent in with first, last, phone
            // we need to insert that into a row

        //TODO Go pull the code Casey made cause idk wtf is going on anymore​

    }

    @Override
    public void deleteContact(long id) {

    }

    @Override
    public Contact getContactById(long id) {
        return null;
    }
}
