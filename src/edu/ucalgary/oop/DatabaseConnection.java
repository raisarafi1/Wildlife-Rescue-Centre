package edu.ucalgary.oop;

import javax.xml.crypto.Data;
import java.sql.*;

public class DatabaseConnection {
    private Connection dbConnect;
    private ResultSet results;

    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "oop", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String selectAnimals() {
        StringBuffer animals = new StringBuffer();

        try {
            Statement statement = dbConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM animals");

            while (results.next()) {
                System.out.println("Animals: " + results.getString("AnimalNickname"));
                animals.append(results.getString("AnimalNickname"));
                animals.append('\n');
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animals.toString();
    }

    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseConnection database = new DatabaseConnection();

        database.createConnection();

        String allAnimals = database.selectAnimals();
        System.out.println("Here is a list of animal nicknames: ");
        System.out.println(allAnimals);

        database.close();
    }

}

