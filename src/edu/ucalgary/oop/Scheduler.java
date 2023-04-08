package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Scheduler {

    public static void schedule(HashMap<Integer, Treatments> treatments, HashMap<Integer, Tasks> tasks, HashMap<Integer, Animal> animals) {

//        System.out.println(treatments);
//        System.out.println(tasks);

        LocalDate today = LocalDate.now();
//        HashMap<Integer, Treatments> filteredTreatments = new HashMap<>();
//        LinkedList<Treatments> filteredTreatments = new LinkedList<>();
        ArrayList<Schedule> toBeAddedToSchedule = new ArrayList<Schedule>();

        System.out.println("Schedule for " + today);

        for (int currentHour = 0; currentHour < 24; currentHour++) {
            System.out.println();

            // treatments to do in a given hour
            System.out.println("All tasks to do at " + currentHour + ":00");

            int finalCurrentHour = currentHour;
            LinkedList<Treatments> filteredTreatments = treatments.entrySet().stream().filter(entry -> {
                        int endHour = entry.getValue().getStartHour() + tasks.get(entry.getValue().getTaskID()).getMaxWindow();

//                if (entry.getValue().getStartHour() <= finalCurrentHour && finalCurrentHour < endHour) {
//                    return true;
//                } else {
//                    return false;
//                }
//                        return entry.getValue().getStartHour() <= finalCurrentHour && finalCurrentHour < endHour;
                        return entry.getValue().getStartHour() == finalCurrentHour;
                    })
                    .map(entry -> {
                        return entry.getValue();
                    })
                    .collect(Collectors.toCollection(() -> new LinkedList<Treatments>()));

//            for (Map.Entry<Integer, Treatments> entry: treatments.entrySet()) {
//                Treatments treatment = entry.getValue();
//                int endHour = treatment.getStartHour() + tasks.get(treatment.getTaskID()).getMaxWindow();
//                if (treatment.getStartHour() <= currentHour && currentHour < endHour) {
//                    filteredTreatments.add(treatment);
//                }
//            }

//            for (int i = 1; i < treatments.size(); i++) {
//                int endHour = treatments.get(i).getStartHour() + tasks.get(treatments.get(i).getTaskID()).getMaxWindow();
//                if (treatments.get(i).getStartHour() <= currentHour && currentHour < endHour) {
//                    filteredTreatments.add(treatments.get(i));
//                }
//            }

            System.out.println("filteredTreatments before: " + filteredTreatments);

            // feeding to do at a given hour
//            if (currentHour >= 0 && currentHour <= 2) {
//                nocturnalFeeding(animals);
//            } else if (currentHour >= 8 && currentHour <= 10) {
//                diurnalFeeding(animals);
//            } else if (currentHour >= 19 && currentHour <= 21) {
//                crepuscularFeeding(animals);
//            }

            // TODO iterate through filtered treatments. the lower the max window is, the higher priority.
            //  add currentHour, taskDescription, animalNickname, number of animals being fed (if split)
            //  into an arrayList(?) called toBeAddedToSchedule.
            //  note: must remove items from lists as they are put into toBeAddedToSchedule
            //  note: when item is added to toBeAddedToSchedule, the duration must be subtracted from timeRemaining in that hour
            //  note: for every item that does not get added to toBeAddedToSchedule, maxWindow for that task decrements


//            Treatments[] filterTreatmentsArray = filteredTreatments.values().toArray(new Treatments[0]);
            for (int i = 0; i < filteredTreatments.size(); i++) {
//                System.out.println("entering for loop");
                int taskID = filteredTreatments.get(i).getTaskID();
                int treatmentMaxWindow = tasks.get(taskID).getMaxWindow();
//                System.out.println(treatmentMaxWindow);

                int treatmentID = filteredTreatments.get(i).getTreatmentID();
                String taskDescription = tasks.get(filteredTreatments.get(i).getTaskID()).getDescription();
                String animalNickname = animals.get(filteredTreatments.get(i).getAnimalID()).getAnimalNickname();
                Schedule schedule = new Schedule(treatmentID, currentHour, taskDescription, animalNickname);

                if (treatmentMaxWindow == 4) {
                    toBeAddedToSchedule.add(schedule);
                    filteredTreatments.remove(i);
                }
            }

            System.out.println("Tasks to be added to the schedule: " + toBeAddedToSchedule);
            System.out.println("filteredTreatments after: " + filteredTreatments);
        }
        System.out.println(toBeAddedToSchedule.size());
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
        System.out.println("foxFeeding: " + foxFeeding);
        System.out.println("raccoonFeeding: " + raccoonFeeding);
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

//            for (int i = 1; i < filteredTreatments.size(); i++) {
//                System.out.println("entering for loop");
//                int taskID = filteredTreatments.get(i).getTaskID();
//                int treatmentMaxWindow = tasks.get(taskID).getMaxWindow();
//                System.out.println(treatmentMaxWindow);
//
//                int treatmentID = filteredTreatments.get(i).getTreatmentID();
//                String taskDescription = tasks.get(filteredTreatments.get(i).getTaskID()).getDescription();
//                String animalNickname = animals.get(filteredTreatments.get(i).getAnimalID()).getAnimalNickname();
//                Schedule schedule = new Schedule(treatmentID, currentHour, taskDescription, animalNickname);
//
//                if (treatmentMaxWindow == 2) {
//                    toBeAddedToSchedule.add(schedule);
//                    filteredTreatments.remove(treatmentID);
//                }
//            }