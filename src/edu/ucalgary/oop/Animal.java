package edu.ucalgary.oop;

public class Animal {
    private int animalID;
    private String animalNickname;
    private Species animalSpecies;

    public Animal(int animalID, String animalNickname, Species animalSpecies) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
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
}
