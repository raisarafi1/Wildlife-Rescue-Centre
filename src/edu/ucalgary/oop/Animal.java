package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class Animal {
    private int animalID;
    private String animalNickname;
    private Species animalSpecies;

    public Animal(int animalID, String animalNickname, Species animalSpecies) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
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

    @Override
    public String toString() {
        return "Animal{" +
                "animalID=" + animalID +
                ", animalNickname='" + animalNickname + '\'' +
                ", animalSpecies=" + animalSpecies +
                '}';
    }

    public static void cageCleaning(HashMap<Integer, Animal> animals) {
        for (int i = 1; i <= animals.size(); i++) {
            if (animals.get(i).animalSpecies == Species.FOX) {
                int foxCleaning = 5;
                System.out.println("it takes " + foxCleaning + " minutes to clean the " + animals.get(i).animalSpecies +  " cage");
            } else if (animals.get(i).animalSpecies == Species.RACCOON) {
                int raccoonCleaning = 5;
                System.out.println("it takes " + raccoonCleaning + " minutes to clean the " + animals.get(i).animalSpecies +  " cage");
            } else if (animals.get(i).animalSpecies == Species.BEAVER) {
                int beaverCleaning = 5;
                System.out.println("it takes " + beaverCleaning + " minutes to clean the " + animals.get(i).animalSpecies +  " cage");
            } else if (animals.get(i).animalSpecies == Species.COYOTE) {
                int coyoteCleaning = 5;
                System.out.println("it takes " + coyoteCleaning + " minutes to clean the " + animals.get(i).animalSpecies +  " cage");
            } else if (animals.get(i).animalSpecies == Species.PORCUPINE) {
                int porcupineCleaning = 10;
                System.out.println("it takes " + porcupineCleaning + " minutes to clean the " + animals.get(i).animalSpecies +  " cage");

            }
        }
    }

}
