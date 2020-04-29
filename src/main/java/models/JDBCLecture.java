package models;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class JDBCLecture {



    public static void main(String[] args) throws SQLException {
        // Instantiate models.Config object
            Config config = new Config();

        // Set up DB driver and make connection
        DriverManager.registerDriver(new Driver());

        // Get connection
        Connection conn = DriverManager.getConnection(
            config.getUrl(),
            config.getUsername(),
            config.getPassword()
        );

        // Create a statement object
        Statement stat = conn.createStatement();

        // Execute some sort of query
            //create query string to get everything in contacts table
        String contactsQuery = "SELECT * FROM contacts";

        ResultSet rs = stat.executeQuery(contactsQuery);

        // Display whats in the result set
        while (rs.next()) {
            System.out.println(rs.getString("first_name" + " "
                    + rs.getString("last_name") + " "
                    + rs.getString("phone_number")));
        }

        System.out.println(rs.getString(1));

        // If we want to add a row to our db, we'll
            // 1: Create a contact object (bean)
            // 2: Creat an SQL Query to insert that contact into our DB on a new row
            // 3: Add to our Dao - instantiate ContactListDao, and use the saveContact() method
        ContactListDAO clDao = new ContactListDAO(); //this DAO allows us to interact with all the contacts

        Contact casey = new Contact(
                "Casey",
                "Friday",
                "2105557777"
        );

        long newContactId = clDao.saveContact(casey); //id of new contact
        Contact newlyCreatedContact = clDao.getContactById(newContactId);

        // INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('casey', 'friday', '2105557777');

        String addContactQuery = String.format(
                "INSERT INTO contacts" +
                "(first_name, last_name, phone_number)" +
                "VALUES ('%s', '%s', '%s')" +
                (newlyCreatedContact.getFirstName() +
                newlyCreatedContact.getLastName() +
                newlyCreatedContact.getPhoneNumber())
        );

        System.out.println("this is the query string we'll be sending to mySql");
        System.out.println(addContactQuery);

        // Execute this SQL query to add the new contact to the DB
        stat.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);

        // If we add Statement.RETURN_GENERATED_KEYS, we can work with the actual MySQL
            // DB table row ID's and reassign those ID's to our Contact objects here in
            // our Java code

        long insertedRowID = 0; //default is 0
        ResultSet ks = stat.getGeneratedKeys();
        if (ks.next()){
            insertedRowID = ks.getLong(1);
            System.out.println("The ID of the newly inserted row is: " + ks.getLong(1));
        }

        System.out.println("Before doing the MySQL id check, " + newlyCreatedContact.getFirstName() + "'s id was: " + newlyCreatedContact.getId());

        // Check to see if the id was returned, or if insertedRowID is still at 0
        if (insertedRowID != 0){
            newlyCreatedContact.setId(insertedRowID);
        }

        System.out.println("Number of rows affected: " + numRows);
    }
}