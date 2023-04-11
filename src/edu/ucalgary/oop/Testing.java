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
        catch(Exception e) {
            passed = true;
        }

        assertTrue("This species is not an animal that the facility takes care of. Must send to another facility.", passed);
    }

    @Test
    /*
     * Ens
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
    /*
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
    /*
     * 
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
    /*
     * 
     */
    /*This simply checks if the cage cleaning function is working accordingly */
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

    @Test
    /*
     The maxWindowTest verifies that the duration is a positive non-zero number
     */
    public void validMaxWindowTest() {
        boolean passed = true;
        int testMaxWindow = 5;
        try {
            Feeding feedingTask = new Feeding("Sniper", Species.FOX, 5, 10, 3, testMaxWindow);
        } catch(IllegalArgumentException e) {
            passed = false;
        } catch(Exception e) {}
        assertTrue("The Feeding class threw an IllegalArgumentException when given a valid max window", passed);
    }

    @Test
    /*
     * 
     */
    public void invalidMaxWindowTest() {
        boolean passed = true;
        int testMaxWindow = 0;
        try {
            Feeding feedingTask = new Feeding("Sniper", Species.FOX, 5, 10, 3, testMaxWindow);
        } catch(IllegalArgumentException e) {
            passed = false;
        } catch(Exception e) {}
        assertFalse("The Feeding class did not throw an IllegalArgumentException when given an invalid max window", passed);

        passed = true;
        testMaxWindow = 8;
        try {
            Feeding feedingTask = new Feeding("Sniper", Species.FOX, 5, 10, 3, testMaxWindow);
        } catch(IllegalArgumentException e) {
            passed = false;
        } catch(Exception e) {}
        assertFalse("The Feeding class did not throw an IllegalArgumentException when given an invalid max window", passed);
    }

    @Test
    /*
     * 
     */
    public void prepTimeTest() {
        boolean passed = true;
        int testPrepTime = 0;
        try {
            Feeding feedingTask = new Feeding("Sniper", Species.FOX, testPrepTime, 10, 3, 5);
        } catch(IllegalArgumentException e) {
            passed = false;
        } catch(Exception e) {}
        assertTrue("The Feeding class threw an IllegalArgumentException when given a valid prep time", passed);

        passed = true;
        testPrepTime = 10;
        try {
            Feeding feedingTask = new Feeding("Sniper", Species.FOX, testPrepTime, 10, 3, 5);
        } catch(IllegalArgumentException e) {
            passed = false;
        } catch(Exception e) {}
        assertTrue("The Feeding class threw an IllegalArgumentException when given a valid prep time", passed);
    }

    @Test
    /*
     * 
     */
    public void invalidPrepTimeTest() {
        boolean passed = true;
        int testPrepTime = 3;
        try {
            Feeding feedingTask = new Feeding("Sniper", Species.FOX, testPrepTime, 10, 3, 5);
        } catch(IllegalArgumentException e) {
            passed = false;
        } catch(Exception e) {}
        assertFalse("The Feeding class did not throw an exeption when given an invalid prep time", passed);

        passed = true;
        testPrepTime = -1;
        try {
            Feeding feedingTask = new Feeding("Sniper", Species.FOX, testPrepTime, 10, 3, 5);
        } catch(IllegalArgumentException e) {
            passed = false;
        } catch(Exception e) {}
        assertFalse("The Feeding class did not throw an exeption when given an invalid prep time", passed);
    }

    @Test
    /*
     * checking if the nocturnalFeedingTest works
     */
    public void nocturnalFeedingTest() {
        boolean passed = true;
        HashMap<Integer, Animal> animals = new HashMap<>();
        animals.put(1, new Animal(1, "The Cool Aligator", Species.FOX));
        animals.put(2, new Animal(2, "The Tired Dolphin", Species.BEAVER));
        animals.put(3, new Animal(3, "The Rumble", Species.RACCOON));

        Scheduler schedule = new Scheduler();
        try {
            Scheduler.nocturnalFeeding(animals);
        } catch(IllegalArgumentException e) {
            passed = false;
        }

        assertTrue("Errors were found in the nocturnalFeeding method", passed);
        //
    }

    @Test
    /*
     * 
     */
    public void diurnalFeedingTest() {
        //
        boolean passed = true;
        HashMap<Integer, Animal> animals = new HashMap<>();
        animals.put(1, new Animal(1, "The Cool Aligator", Species.FOX));
        animals.put(2, new Animal(2, "The Tired Dolphin", Species.BEAVER));
        animals.put(3, new Animal(3, "The Rumble", Species.RACCOON));

        Scheduler schedule = new Scheduler();
        try {
            Scheduler.nocturnalFeeding(animals);
        } catch(IllegalArgumentException e) {
            passed = false;
        }

        assertTrue("Errors were found in the diurnalFeeding method", passed);
        //
    }

    @Test
    /*
     * 
     */
    public void crepuscularFeedingTest() {
        //
        boolean passed = true;
        HashMap<Integer, Animal> animals = new HashMap<>();
        animals.put(1, new Animal(1, "The Cool Aligator", Species.FOX));
        animals.put(2, new Animal(2, "The Tired Dolphin", Species.BEAVER));
        animals.put(3, new Animal(3, "The Rumble", Species.RACCOON));

        Scheduler schedule = new Scheduler();
        try {
            Scheduler.nocturnalFeeding(animals);
        } catch(IllegalArgumentException e) {
            passed = false;
        }

        assertTrue("Errors were found in the crepuscularFeeding method", passed);
        //
    }

}
