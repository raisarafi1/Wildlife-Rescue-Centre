package edu.ucalgary.oop;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;

public class DatabaseConnection {
    private Connection dbConnect;
    private ResultSet results;

    /**
     * Creates a connection to the treatments database
     *
     * @return
     */
    public Connection createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "oop", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnect;
    }

    /**
     * Executes an sql query that returns all the treatment tasks from the database
     * and stores it into a hashmap called treatmentsList
     * @return
     */
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

    /**
     * Executes an sql query that returns all the animals from the database
     * and stores it into a hashmap called animalList
     * @return
     */
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

    /**
     * Executes an sql query that returns all the animals from the database
     * and stores it into a hashmap called animalList
     * @return
     */
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

    /**
     * Trys to close the database connection
     */
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


        int foxPrepTime = 5;
        int foxFeedingTime = 5;

        int raccoonFeedingTime = 5;

        int coyotePrepTime = 10;
        int coyoteFeedingTime = 5;

        int porcupineFeedingTime = 5;

        HashMap<Integer, Tasks> allTasks = database.retrieveTasksInfo();
        HashMap<Integer, Treatments> allTreatments = database.retrieveTreatmentsInfo();
        HashMap<Integer, Animal> allAnimals = database.retrieveAnimalInfo();


        int numOfFoxes = 0;
        for (int i = 1; i <= allAnimals.size(); i++) {
            if (allAnimals.get(i).getAnimalSpecies() == Species.FOX && !allAnimals.get(i).getAnimalNickname().contains(" and "))
                numOfFoxes++;
        }

        int numOfCoyotes = 0;
        for (int i = 1; i <= allAnimals.size(); i++) {
            if (allAnimals.get(i).getAnimalSpecies() == Species.COYOTE && !allAnimals.get(i).getAnimalNickname().contains(" and "))
                numOfCoyotes++;
        }

        int numofPorcupines = 0;
        for (int i = 1; i <= allAnimals.size(); i++) {
            if (allAnimals.get(i).getAnimalSpecies() == Species.PORCUPINE && !allAnimals.get(i).getAnimalNickname().contains(" and "))
                numofPorcupines++;
        }

        int numOfRaccoons = 0;
        for (int i = 1; i <= allAnimals.size(); i++) {
            if (allAnimals.get(i).getAnimalSpecies() == Species.RACCOON && !allAnimals.get(i).getAnimalNickname().contains(" and "))
                numOfRaccoons++;
        }

        int numOfBeavers = 0;
        for (int i = 1; i <= allAnimals.size(); i++) {
            if (allAnimals.get(i).getAnimalSpecies() == Species.BEAVER && !allAnimals.get(i).getAnimalNickname().contains(" and "))
                numOfBeavers++;
        }


        // creating new treatmentIDs
        int maxTreatmentID = allTreatments
                .entrySet()
                .stream()
                .map(treatment -> treatment.getValue().getTreatmentID())
                .max(Integer::compareTo)
                .get();
        int newTreatmentID = maxTreatmentID + 1;


        // creating new taskIDs
        int maxTaskID = allTasks
                .entrySet()
                .stream()
                .map(treatment -> treatment.getValue().getTaskID())
                .max(Integer::compareTo)
                .get();
        int newTaskID = maxTaskID + 1;
        System.out.println(newTaskID);


        // creating new animalIDs
        int maxAnimalID = allAnimals
                .entrySet()
                .stream()
                .map(treatment -> treatment.getValue().getAnimalID())
                .max(Integer::compareTo)
                .get();
        int newAnimalID = maxAnimalID + 1;

        // Adding extra animals

        Animal allFoxes = new Animal(newAnimalID, "All foxes");
        allAnimals.put(allFoxes.getAnimalID(), allFoxes);

        newAnimalID++;
        Animal allCoyotes = new Animal(newAnimalID, "All coyotes");
        allAnimals.put(allCoyotes.getAnimalID(), allCoyotes);

        System.out.println(allAnimals);

        // Adding extra tasks
        Tasks feedingPorcupines = new Tasks(newTaskID, "Feeding porcupines", 5, 3);
        allTasks.put(feedingPorcupines.getTaskID(), feedingPorcupines);

        newTaskID++;
        Tasks feedingFoxes = new Tasks(newTaskID, "Feeding foxes", 5+(5*numOfFoxes), 3);
        allTasks.put(feedingFoxes.getTaskID(), feedingFoxes);

        newTaskID++;
        Tasks feedingCoyotes = new Tasks(newTaskID, "Feeding coyotes", 10+(5*numOfCoyotes), 3);
        allTasks.put(feedingCoyotes.getTaskID(), feedingCoyotes);

        newTaskID++;
        Tasks feedingRaccoons = new Tasks(newTaskID, "Feeding raccoons", 5, 3);
        allTasks.put(feedingRaccoons.getTaskID(), feedingRaccoons);

        newTaskID++;
        Tasks feedingBeavers = new Tasks(newTaskID, "Feeding beavers", 5, 3);
        allTasks.put(feedingBeavers.getTaskID(), feedingBeavers);

        newTaskID++;
        Tasks cleaningPorcupineCage = new Tasks(newTaskID, "Cleaning porcupine cage", 10, 24);
        allTasks.put(cleaningPorcupineCage.getTaskID(), cleaningPorcupineCage);

        newTaskID++;
        Tasks cleaningCoyoteCage = new Tasks(newTaskID, "Cleaning coyote cage", 5, 24);
        allTasks.put(cleaningCoyoteCage.getTaskID(), cleaningCoyoteCage);

        newTaskID++;
        Tasks cleaningFoxCage = new Tasks(newTaskID, "Cleaning fox cage", 5, 24);
        allTasks.put(cleaningFoxCage.getTaskID(), cleaningFoxCage);

        newTaskID++;
        Tasks cleaningRaccoonCage = new Tasks(newTaskID, "Cleaning raccoon cage", 5, 24);
        allTasks.put(cleaningRaccoonCage.getTaskID(), cleaningRaccoonCage);

        newTaskID++;
        Tasks cleaningBeaverCage = new Tasks(newTaskID, "Cleaning beaver cage", 5, 24);
        allTasks.put(cleaningBeaverCage.getTaskID(), cleaningBeaverCage);

        System.out.println(allTasks);


        // Adding extra treatments
        // Treatment to feed the foxes
        Treatments feedingFoxesTreatment = new Treatments(newTreatmentID, allFoxes.getAnimalID(), feedingFoxes.getTaskID(), 0);
        allTreatments.put(feedingFoxesTreatment.getTreatmentID(), feedingFoxesTreatment);

        newTreatmentID++;

        // Treatment to feed the coyotes
        Treatments feedingCoyotesTreatment = new Treatments(newTreatmentID, allCoyotes.getAnimalID(), feedingCoyotes.getTaskID(), 19);
        allTreatments.put(feedingCoyotesTreatment.getTreatmentID(), feedingCoyotesTreatment);


        // Treatments to feed the porcupines, raccoons, and beavers
        for (int i = 1; i <= allAnimals.size(); i++) {
            if (allAnimals.get(i).getAnimalSpecies() == Species.PORCUPINE) {
                newTreatmentID++;
                Treatments feedingPorcupinesTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), feedingPorcupines.getTaskID(), 19);
                allTreatments.put(feedingPorcupinesTreatment.getTreatmentID(), feedingPorcupinesTreatment);
            }
            if (allAnimals.get(i).getAnimalSpecies() == Species.RACCOON) {
                newTreatmentID++;
                Treatments feedingRaccoonsTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), feedingRaccoons.getTaskID(), 19);
                allTreatments.put(feedingRaccoonsTreatment.getTreatmentID(), feedingRaccoonsTreatment);
            }
            if (allAnimals.get(i).getAnimalSpecies() == Species.BEAVER) {
                newTreatmentID++;
                Treatments feedingBeaversTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), feedingBeavers.getTaskID(), 19);
                allTreatments.put(feedingBeaversTreatment.getTreatmentID(), feedingBeaversTreatment);
            }
        }

        // Treatments to clean the cages
        for (int i = 1; i <= allAnimals.size(); i++) {
            if (allAnimals.get(i).getAnimalSpecies() == Species.PORCUPINE) {
                newTreatmentID++;
                Treatments cleaningPorcupinesTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), cleaningPorcupineCage.getTaskID(), 0);
                allTreatments.put(cleaningPorcupinesTreatment.getTreatmentID(), cleaningPorcupinesTreatment);
            }
            if (allAnimals.get(i).getAnimalSpecies() == Species.COYOTE) {
                newTreatmentID++;
                Treatments cleaningCoyotesTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), cleaningCoyoteCage.getTaskID(), 0);
                allTreatments.put(cleaningCoyotesTreatment.getTreatmentID(), cleaningCoyotesTreatment);
            }
            if (allAnimals.get(i).getAnimalSpecies() == Species.FOX) {
                newTreatmentID++;
                Treatments cleaningFoxesTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), cleaningFoxCage.getTaskID(), 0);
                allTreatments.put(cleaningFoxesTreatment.getTreatmentID(), cleaningFoxesTreatment);
            }
            if (allAnimals.get(i).getAnimalSpecies() == Species.RACCOON) {
                newTreatmentID++;
                Treatments cleaningRaccoonsTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), cleaningRaccoonCage.getTaskID(), 0);
                allTreatments.put(cleaningRaccoonsTreatment.getTreatmentID(), cleaningRaccoonsTreatment);
            }
            if (allAnimals.get(i).getAnimalSpecies() == Species.BEAVER) {
                newTreatmentID++;
                Treatments cleaningBeaversTreatment = new Treatments(newTreatmentID, allAnimals.get(i).getAnimalID(), cleaningBeaverCage.getTaskID(), 0);
                allTreatments.put(cleaningBeaversTreatment.getTreatmentID(), cleaningBeaversTreatment);
            }

        }
        System.out.println(allTreatments);


        Scheduler.schedule(allTreatments, allTasks, allAnimals);


        database.close();

        LocalDate today = LocalDate.now();
        StringBuilder data = Scheduler.getData();

        try {
            FileWriter file = new FileWriter("schedule.txt");  // Create a FileWriter object
            file.write("Schedule for " + today);   // Write to the file
            file.write(String.valueOf(data));
            file.close();   // Close the file
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();   // Print the stack trace if an error occurs
        }
    }

}
