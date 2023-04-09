package edu.ucalgary.oop;

import javax.xml.crypto.Data;
import java.sql.*;
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

    public HashMap<Integer, Treatments> retrieveTreatmentsInfo() {
        HashMap<Integer, Treatments> treatmentsList = new HashMap<>();

        try {
            Statement statement = dbConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM treatments");

            while (results.next()) {
                Treatments treatments = new Treatments();
                String stringTreatmentID = results.getString("TreatmentID");
                int treatmentID = Integer.parseInt(stringTreatmentID);
                treatments.setTreatmentID(treatmentID);
                String animalID = results.getString("AnimalID");
                treatments.setAnimalID(Integer.parseInt(animalID));
                String taskID = results.getString("TaskID");
                treatments.setTaskID(Integer.parseInt(taskID));
                String startHour = results.getString("StartHour");
                treatments.setStartHour(Integer.parseInt(startHour));

                treatmentsList.put(treatmentID, treatments);
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatmentsList;
    }

    public HashMap<Integer, Animal> retrieveAnimalInfo() {
        HashMap<Integer, Animal> animalList = new HashMap<>();

        try {
            Statement statement = dbConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM animals");

            while (results.next()) {
                Animal animal = new Animal();
                String strAnimalID = results.getString("AnimalID");
                int animalID = Integer.parseInt(strAnimalID);
                animal.setAnimalID(animalID);
                String animalNickname = results.getString("AnimalNickname");
                animal.setAnimalNickname((animalNickname));
                String animalSpecies = results.getString("AnimalSpecies");
                animal.setAnimalSpecies(Species.fromString(animalSpecies));
                animalList.put(animalID, animal);

            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animalList;
    }

    public HashMap<Integer, Tasks> retrieveTasksInfo() {
        HashMap<Integer, Tasks> tasksList = new HashMap<>();

        try {
            Statement statement = dbConnect.createStatement();
            results = statement.executeQuery("SELECT * FROM tasks");

            while (results.next()) {
                Tasks task = new Tasks();
                String strTaskID = results.getString("TaskID");
                int taskID = Integer.parseInt(strTaskID);
                task.setTaskID(taskID);
                String description = results.getString("Description");
                task.setDescription(description);
                String duration = results.getString("Duration");
                task.setDuration(Integer.parseInt(duration));
                String maxWindow = results.getString("MaxWindow");
                task.setMaxWindow(Integer.parseInt(maxWindow));

                tasksList.put(taskID, task);
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

        HashMap<Integer, Tasks> allTasks = database.retrieveTasksInfo();
        // TODO @raisa

        // find max task id. increment that by 1 to get the new unused id.

        // use the new id for feeding (or cleaning)

        // increment to get another id for cleaning (or feeding)

        // use these ids bellow to create treatments and add it to the hash map

//        System.out.println("Tasks Table: ");
//        System.out.println(allTasks.toString());
//
//        System.out.println();

        HashMap<Integer, Treatments> allTreatments = database.retrieveTreatmentsInfo();

        int maxTreatmentID = allTreatments
                .entrySet()
                .stream()
                .map(treatment -> treatment.getValue().getTreatmentID())
                .max(Integer::compareTo)
                .get();

        int newTreatmentID = maxTreatmentID++;

        allTreatments.put(newTreatmentID, new Treatments(newTreatmentID, 1, 1, 1));
        newTreatmentID++;
        allTreatments.put(newTreatmentID, new Treatments(newTreatmentID, 1, 1, 1));

//        System.out.println("Treatments Table: ");
//        System.out.println(allTreatments.toString());
//
//        System.out.println();



        HashMap<Integer, Animal> allAnimals = database.retrieveAnimalInfo();
//        System.out.println("Animal Table: ");
//        System.out.println(allAnimals.toString());

//        Scheduler.schedule(allTreatments, allTasks, allAnimals); // TODO @raisa the real schedule
        Scheduler.schedule2(allTreatments, allTasks, allAnimals);

        // feeding and cleaning methods are called
//        Animal.nocturnalFeeding(allAnimals);
//        Animal.diurnalFeeding(allAnimals);
//        Animal.crepuscularFeeding(allAnimals);
//        Animal.cageCleaning(allAnimals);

        database.close();
    }

}

