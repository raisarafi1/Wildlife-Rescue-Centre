package edu.ucalgary.oop;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

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

    public LinkedList<Treatments> retrieveTreatmentsInfo() {
        LinkedList<Treatments> treatmentsList = new LinkedList<>();

        try {
            Statement statement = dbConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM treatments");

            while (results.next()) {
                Treatments treatments = new Treatments();
                String animalID = results.getString("AnimalID");
                treatments.setAnimalID(Integer.parseInt(animalID));
                String taskID = results.getString("TaskID");
                treatments.setTaskID(Integer.parseInt(taskID));
                String startHour = results.getString("StartHour");
                treatments.setStartHour(Integer.parseInt(startHour));

                treatmentsList.add(treatments);
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatmentsList;
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

        LinkedList<Treatments> allTreatments = database.retrieveTreatmentsInfo();
        System.out.println("Here is a list of Treatments: ");
        System.out.println(allTreatments.toString());

        database.close();
    }

}

