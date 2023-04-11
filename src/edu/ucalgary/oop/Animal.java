/**
 @authors Raisa Rafi, Rida Khan, Mohamed Ebdalla, Joshua Debele
 @version 4.1
 @since 1.0
 */

package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class Animal {
    private int animalID;
    private String animalNickname;
    private Species animalSpecies;

    /**
     * Creates an animal class that assigns a unique id, nickname, and species to each animal
     * @param animalID
     * @param animalNickname
     * @param animalSpecies
     * @throws IllegalArgumentException
     */
    public Animal(int animalID, String animalNickname, Species animalSpecies) throws IllegalArgumentException, NotSpeciesException {
         try {
            String species = animalSpecies.toString();
            Species.valueOf(species);
        } catch(IllegalArgumentException e) {
             throw new NotSpeciesException("Invalid species, must be one of..");
         }
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
    }

    /**
     *
     * @param animalID
     * @param animalNickname
     * Animal constructor given animalID and animalNickname
     */

    public Animal(int animalID, String animalNickname) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
    }

    /**
     * Default constructor
     */
    public Animal() {

    }

    /**
     *
     * @return the animalID
     */
    public int getAnimalID() {
        return animalID;
    }

    /**
     *
     * @return the animalNickname
     */
    public String getAnimalNickname() {
        return animalNickname;
    }

    /**
     *
     * @return animalSpecials
     */
    public Species getAnimalSpecies() {
        return animalSpecies;
    }

    /**
     *
     * @param animalID being set to animalID
     */

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    /**
     * setting animalNickname
     * @param animalNickname
     */

    public void setAnimalNickname(String animalNickname) {
        this.animalNickname = animalNickname;
    }

    /**
     * setting animalSpecies
     * @param animalSpecies
     */
    public void setAnimalSpecies(Species animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    /**
     * The toString method generates a string that is properly formatted and includes all the relevant details about the animal
     */
    @Override
    public String toString() {
        return "Animal{" +
                "animalID=" + animalID +
                ", animalNickname='" + animalNickname + '\'' +
                ", animalSpecies=" + animalSpecies +
                '}';
    }

}
