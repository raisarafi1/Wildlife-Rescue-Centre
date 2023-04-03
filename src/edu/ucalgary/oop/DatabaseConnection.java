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

//    public LinkedList<Animal> retrieveAnimalInfo() {
//        LinkedList<Animal> animalList = new LinkedList<>();
//
//        try {
//            Statement statement = dbConnect.createStatement();
//            results = statement.executeQuery("SELECT * FROM animals");
//
//            while (results.next()) {
//                Animal animal = new Animal();
//                String animalID = results.getString("AnimalID");
//                animal.setAnimalID(Integer.parseInt(animalID));
//                String animalNickname = results.getString("AnimalNickname");
//                animal.setAnimalNickname((animalNickname));
//                String animalSpecies = results.getString("AnimalSpecies");
//                animal.setAnimalSpecies(animalSpecies);
//
//                animalList.add(animal);
//            }
//            statement.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return animalList;
//    }

    public LinkedList<Tasks> retrieveTasksInfo() {
        LinkedList<Tasks> tasksList = new LinkedList<>();

        try {
            Statement statement = dbConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM tasks");

            while (results.next()) {
                Tasks task = new Tasks();
                String taskID = results.getString("TaskID");
                task.setTaskID(Integer.parseInt(taskID));
                String description = results.getString("Description");
                task.setDescription(description);
                String duration = results.getString("Duration");
                task.setDuration(Integer.parseInt(duration));
                String maxWindow = results.getString("MaxWindow");
                task.setMaxWindow(Integer.parseInt(maxWindow));

                tasksList.add(task);
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tasksList;
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
        System.out.println("Treatments Table: ");
        System.out.println(allTreatments.toString());

        System.out.println();

        LinkedList<Tasks> allTasks = database.retrieveTasksInfo();
        System.out.println("Tasks Table: ");
        System.out.println(allTasks.toString());

        database.close();
    }

}

