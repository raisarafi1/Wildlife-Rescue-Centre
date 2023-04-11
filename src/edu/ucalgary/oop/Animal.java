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
    public Animal(int animalID, String animalNickname, Species animalSpecies) throws IllegalArgumentException{
        /*
         try {
            String species = animalSpecies.toString();
            Species.valueOf(species);
        } catch(IllegalArgumentException e) {
            throw new NotSpeciesException("");
        }
         */
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
    }

    public Animal(int animalID, String animalNickname) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
    }

    public Animal() {

    }

    public int getAnimalID() {
        return animalID;
    }

    public String getAnimalNickname() {
        return animalNickname;
    }

    public Species getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public void setAnimalNickname(String animalNickname) {
        this.animalNickname = animalNickname;
    }

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
