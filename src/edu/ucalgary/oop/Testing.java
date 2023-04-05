package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class Testing {
    @Test
    /*
     The SpeciesTest will check and see if the animal that is being taken care
     is part of the Enumeration class.
     */
    public void SpeciesExceptionTest() {
        boolean passed = false;
        try {
            Animal animal = new Animal(20, 'dolphin', ELEPHANT);
        }
        catch(NotSpeciesException e) {
            passed = true;
        }
        catch(Exception e) {}
        assertTrue(passed);
    }

    public void SpeciesTest() {

    }
    @Test 
    /*
     The durationTest verifies that the duration is a positive non-zero number
     */
    public void durationTest() {

    }

    @Test
    /*
     The maxWindowTest verifies that the duration is a positive non-zero number
     */
    public void maxWindowTest() {

    }

}
