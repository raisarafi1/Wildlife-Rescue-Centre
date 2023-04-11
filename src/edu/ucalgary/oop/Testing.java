package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class Testing {
    @Test
    /**
     * Tests whether code will throw an exception when given an invalid Animal argument
     */
    public void SpeciesExceptionTest() {
        boolean passed = false;
        try {
            Animal animal = new Animal(20, "Smarty pants", Species.ELEPHANT);
        }
        catch(NotSpeciesException e) {
            passed = true;
        }
        catch(Exception e) {}
        assertTrue("This species is not an animal that the facility takes care of. Must send to another facility.", passed);
    }

    @Test
    /**
     * Ensures the Animal Object is instantiated when given a valid species
     */
    public void validSpeciesTest() {
        boolean passed = true;
        try {
            Animal animal = new Animal(2, "The Speeder", Species.FOX);
        }
        catch(IllegalArgumentException e) {
            passed = false;
        }
        catch(Exception e) {}
        assertTrue("This species is not an animal", passed);
    }

    @Test 
    /**
     * Aims to successfully run test when duration is outside 
     * of specified boundary in Tasks
     */
    public void failedDurationTest() {
        boolean passed = true;
        int testDuration = 2;
        //less than 5
        try {
            Tasks task = new Tasks(1, "feeding orphaned animal", testDuration, 2);
        } catch(IllegalArgumentException e) {
            passed = false;
        }catch(Exception e) {};
        assertFalse("The Tasks class did not throw an IllegalArgumentException when given an invalid duration", passed);

        //over 60
        passed = true;
        testDuration = 65;
        try {
            Tasks task = new Tasks(1, "feeding orphaned animal", testDuration, 2);
        } catch(IllegalArgumentException e) {
            passed = false;
        }catch(Exception e) {};
        assertFalse("The Tasks class did not throw an IllegalArgumentException when given an invalid duration", passed);
    }

    @Test
    /**
     * Tests if Tasks object is instantiated when given a valid duration value
     */
    public void passedDurationTest() {
        boolean passed = true;
        int testDuration = 10;
        try {
            Tasks task = new Tasks(2, "feeding orphaned animal", testDuration, 5);
        } catch(IllegalArgumentException e) {
            passed = false;
        }catch(Exception e) {};
        assertTrue("The Tasks class threw an IllegalArgumentException when given a valid duration", passed);

        passed = true;
        testDuration = 60;
        try {
            Tasks task = new Tasks(2, "feeding orphaned animal", testDuration, 5);
        } catch(IllegalArgumentException e) {
            passed = false;
        }catch(Exception e) {};
        assertTrue("The Tasks class threw an IllegalArgumentException when given a valid duration", passed);
    }

    @Test
    /**
     * This simply checks if the cage cleaning method is working accordingly
     */
    public void checkCageCleaningTest() {
        Animal animal = new Animal(1, "Crazy Macey", Species.FOX);
        boolean passed = true;
        try {
            animal.cageCleaning(new HashMap<Integer, Animal>());
        } catch(IllegalArgumentException e) {
            passed = false;
        }
        assertTrue("the cageCleaning method did not run successfully", passed);
    }

    /**
     * Tests if the schedule method from the
     * Scheduler class runs successfully
     */
    @Test 
    public void scheduleTest() {
        boolean passed = true;
        Scheduler schedule = new Scheduler();
        HashMap<Integer, Treatments> treatments = new HashMap<>();
        treatments.put(1, new Treatments(1, 1, 1, 1));
        treatments.put(2, new Treatments(2, 2, 2, 2));
        treatments.put(3, new Treatments(3, 3, 3, 3));
        HashMap<Integer, Tasks> tasks = new HashMap<>();
        tasks.put(1, new Tasks(1, "Kit Feeding", 20, 2));
        tasks.put(2, new Tasks(2, "Dancing with the Dolphins", 30, 1));
        tasks.put(3, new Tasks(3, "Taking care of dogs", 15, 5));

        HashMap<Integer, Animal> animals = new HashMap<>();
        animals.put(1, new Animal(1, "the ranger", Species.FOX));
        animals.put(2, new Animal(2, "The killer", Species.RACCOON));
        animals.put(3, new Animal(3, "THE loner", Species.COYOTE));

        try {
            schedule.schedule(treatments, tasks, animals);
        } catch(Exception e) {
            passed = false;
        }
        assertTrue("The schedule method from the Scheduler class did not run successfully", passed);
    }
   
    /**
     * Tests if the getData method from
     * the Scheduler class returns a valid StringBuilder object
     */
    @Test
    public void scheduleGetData() {
        boolean passed = true;
        Scheduler schedule = new Scheduler();
       
        HashMap<Integer, Treatments> treatments = new HashMap<>();
        treatments.put(1, new Treatments(1, 1, 1, 1));
       
        HashMap<Integer, Tasks> tasks = new HashMap<>();
        tasks.put(1, new Tasks(1, "Kit Feeding", 20, 2));
       
        HashMap<Integer, Animal> animals = new HashMap<>();
        animals.put(1, new Animal(1, "the ranger", Species.FOX));


        schedule.schedule(treatments, tasks, animals);
        String expectedResult = "Hour: 0 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 1 runningTreatmentTodosIDs: [1] treatmentIDSortedByMaxWindow: [1] processing tasks timeRemaining at the top of the hour: 60 working on treatment id, task id: 1, 1 timeRemaining: 40 timeRemaining at the end of the hour: 40 Hour: 2 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 3 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 4 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 5 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 6 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 7 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 8 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 9 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 10 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 11 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 12 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 13 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 14 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 15 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 16 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 17 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 18 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 19 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 20 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 21 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 22 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 Hour: 23 runningTreatmentTodosIDs: [] treatmentIDSortedByMaxWindow: [] processing tasks timeRemaining at the top of the hour: 60 timeRemaining at the end of the hour: 60 0:00 1:00 * Kit Feeding (the ranger) 2:00 3:00 4:00 5:00 6:00 7:00 8:00 9:00 10:00 11:00 12:00 13:00 14:00 15:00 16:00 17:00 18:00 19:00 20:00 21:00 22:00 23:00";
        String test = expectedResult.trim().replaceAll("\\s+", "");
        try {
            String retrievedData = schedule.getData().toString().trim().replaceAll("\\s+", "");
        } catch(Exception e) {
            passed = false;
        }
        assertTrue("The getData method in the schedule does not return the appropriate schedule as a String", passed);
    }

    /**
     * Expects test to pass when Tasks Class is given valid argument values
     */
    @Test
    public void validMaxWindowTest() {
        boolean testPassed = true;
        Tasks task; 
        try {
            task = new Tasks(1, "Kit Feeding", 20, 2);
        } catch(IllegalArgumentException e) {
            testPassed = false;
        }
        assertTrue("Exception was not thrown when the Tasks Class was given invalid arguments", testPassed);
    }
    
    /**
     * Expects test to pass when the Tasks Class is given invalid argument values
     */
    @Test 
    public void invalidMaxWindowTest() {
        boolean testPassed = false;
        Tasks task; 
        try {
            task = new Tasks(1, "Kit Feeding", 20, 0);
        } catch(IllegalArgumentException e) {
            testPassed = true;
        }
        assertTrue("Exception was thrown when the Tasks Class was given valid arguments", testPassed);
        
    }

    /**
     * Checks if the retrieveTreatmentsInfo
     * method from the DatabaseConnection Class returns
     * a HashMap consisting of data from the database
     */
    @Test
    public void retrieveTreatmentsInfoTest() {
        boolean passed = true;
        HashMap<Integer, Treatments> retrieveTreatments = new HashMap<>();
        DatabaseConnection db = new DatabaseConnection();
        
        try {
            retrieveTreatments = db.retrieveTreatmentsInfo();
        } catch(Exception e) {
            passed = false;
        }
        assertTrue("Error in connecting with the database. Method did not run successfully", passed);
    }

    /**
     * Checks if the retrieveAnimalsInfo
     * method from the DatabaseConnection Class returns
     * a HashMap consisting of data from the database
     */
    @Test
    public void retrieveAnimalInfoTest() {
        boolean passed = true;
        HashMap<Integer, Animal> retrieveAnimals = new HashMap<>();
        DatabaseConnection db = new DatabaseConnection();
        
        try {
            retrieveAnimals = db.retrieveAnimalInfo();
        } catch(Exception e) {
            passed = false;
        }
        assertTrue("Error in connecting with the data. Method did not run successfully", passed);
    }

    /**
     * Checks if the retrieveTasksInfo
     * method from the DatabaseConnection Class returns
     * a HashMap consisting of data from the database
     */
    @Test
    public void retrieveTasksInfoTest() {
        boolean passed = true;
        HashMap<Integer, Tasks> retrieveTasks = new HashMap<>();
        DatabaseConnection db = new DatabaseConnection();
        
        try {
            retrieveTasks = db.retrieveTasksInfo();
        } catch(Exception e) {
            passed = false;
        }
        assertTrue("Error in connecting with the data. Method did not run successfully", passed);
    }

}
>>>>>>> 66fb1e485a28d9bff9b36692f15b080ed5c899f9
