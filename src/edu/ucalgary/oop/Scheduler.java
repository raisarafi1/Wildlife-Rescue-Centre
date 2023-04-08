package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Scheduler {

    public static void schedule(HashMap<Integer, Treatments> treatments, HashMap<Integer, Tasks> tasks, HashMap<Integer, Animal> animals) {

//        System.out.println(treatments);
//        System.out.println(tasks);

        LocalDate today = LocalDate.now();
        HashMap<Integer, Treatments> filteredTreatments = new HashMap<>();

        System.out.println("Schedule for " + today);

        for (int currentHour = 0; currentHour < 24; currentHour++) {

            System.out.println("All tasks to do at " + currentHour);
            for (int i = 1; i < treatments.size(); i++) {
                int endHour = treatments.get(i).getStartHour() + tasks.get(treatments.get(i).getTaskID()).getMaxWindow();
                if (treatments.get(i).getStartHour() <= currentHour && currentHour < endHour) {
                    filteredTreatments.put(treatments.get(i).getTreatmentID(), treatments.get(i));
                }
            }

            System.out.println(filteredTreatments);

            // feeding lists at different times being created
            if (currentHour >= 0 && currentHour <= 2) {
                nocturnalFeeding(animals);
            } else if (currentHour >= 8 && currentHour <= 10) {
                diurnalFeeding(animals);
            } else if (currentHour >= 19 && currentHour <= 21) {
                crepuscularFeeding(animals);
            }
        }
    }

    public static void nocturnalFeeding(HashMap<Integer, Animal> animals) {
        // foxes and raccoons
        int foxPrepTime = 5;
        int foxFeedingTime = 5;
        int numOfFoxes = 0;

        int racoonPrepTime = 0;
        int raccoonFeedingTime = 5;
        int numOfRaccoons = 0;

        ArrayList<Feeding> foxFeeding = new ArrayList<>();
        ArrayList<Feeding> raccoonFeeding = new ArrayList<>();


        for (int i = 1; i <= animals.size(); i++) {
            // only increment the number of foxes if it's an adult fox. Will not increment for kits.
            if (animals.get(i).getAnimalSpecies() == Species.FOX && !animals.get(i).getAnimalNickname().contains(" and ")) {
                numOfFoxes++;

                Feeding feeding = new Feeding(
                        animals.get(i).getAnimalNickname(),
                        animals.get(i).getAnimalSpecies(),
                        foxPrepTime,
                        foxFeedingTime,
                        0,
                        3
                );

                foxFeeding.add(feeding);
            }

            if (animals.get(i).getAnimalSpecies() == Species.RACCOON) {
                numOfRaccoons++;
                Feeding feeding = new Feeding(
                        animals.get(i).getAnimalNickname(),
                        animals.get(i).getAnimalSpecies(),
                        racoonPrepTime,
                        raccoonFeedingTime,
                        0,
                        3
                );

                raccoonFeeding.add(feeding);
            }
        }
        System.out.println(foxFeeding);
        System.out.println(raccoonFeeding);
    }

    public static void diurnalFeeding(HashMap<Integer, Animal> animals) {
        // beavers
        int beaverPrepTime = 0;
        int beaverFeedingTime = 5;
        int numOfBeavers = 0;

        ArrayList<Feeding> beaverFeeding = new ArrayList<>();

        // to determine the amount of beavers
        for (int i = 1; i < animals.size(); i++) {
            if (animals.get(i).getAnimalSpecies() == Species.BEAVER) {
                numOfBeavers++;
                Feeding feeding = new Feeding(
                        animals.get(i).getAnimalNickname(),
                        animals.get(i).getAnimalSpecies(),
                        beaverPrepTime,
                        beaverFeedingTime,
                        8,
                        3);
                beaverFeeding.add(feeding);
            }
        }
        System.out.println(beaverFeeding);
    }

    public static void crepuscularFeeding(HashMap<Integer, Animal> animals) {
        // coyotes and porcupines
        int coyotePrepTime = 10;
        int coyoteFeedingTime = 5;
        int numOfCoyotes = 0;

        int porcupinePrepTime = 0;
        int porcupineFeedingTime = 5;
        int numOfPorcupines = 0;

        ArrayList<Feeding> coyoteFeeding = new ArrayList<>();
        ArrayList<Feeding> porcupineFeeding = new ArrayList<>();


        for (int i = 1; i <= animals.size(); i++) {
            if (animals.get(i).getAnimalSpecies() == Species.COYOTE) {
                numOfCoyotes++;
                Feeding feeding = new Feeding(
                        animals.get(i).getAnimalNickname(),
                        animals.get(i).getAnimalSpecies(),
                        coyotePrepTime,
                        coyoteFeedingTime,
                        19,
                        3
                );
                coyoteFeeding.add(feeding);
            }
            if (animals.get(i).getAnimalSpecies() == Species.PORCUPINE) {
                numOfPorcupines++;
                Feeding feeding = new Feeding(
                        animals.get(i).getAnimalNickname(),
                        animals.get(i).getAnimalSpecies(),
                        porcupinePrepTime,
                        porcupineFeedingTime,
                        19,
                        3
                );
                porcupineFeeding.add(feeding);
            }
        }
        System.out.println(coyoteFeeding);
        System.out.println(porcupineFeeding);

    }
}
