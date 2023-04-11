package edu.ucalgary.oop;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;


public class Scheduler {

    private static StringBuilder scheduleString;

    public static void schedule(HashMap<Integer, Treatments> treatments, HashMap<Integer, Tasks> tasks, HashMap<Integer, Animal> animals) {
        scheduleString = new StringBuilder();

        LinkedList<Integer> runningTreatmentTodosIDs = new LinkedList<>();

        int currentHour = 0;
        while (currentHour < 24) {
            int timeRemaining = 60;
            System.out.println("Hour: " + currentHour);
            scheduleString.append("\n");
            scheduleString.append(currentHour + ":00");
            scheduleString.append("\n");

            // get a set of treatment ids that belong to the hour and accumulate to treatment_todo
            int finalCurrentHour = currentHour;
            LinkedList<Integer> justThisHour = treatments
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().getStartHour() == finalCurrentHour)
                    .map(entry -> entry.getValue().getTreatmentID())
                    .collect(Collectors.toCollection(LinkedList::new));
            runningTreatmentTodosIDs.addAll(justThisHour);
            System.out.println("runningTreatmentTodosIDs: " + runningTreatmentTodosIDs);

            // find the treatment that has the lowest max window
            LinkedList<Integer> treatmentIDSortedByMaxWindow = runningTreatmentTodosIDs.stream().sorted((treatmentID1, treatmentID2) -> {
                // Comparator returns
                // a negative integer, zero, or a positive integer
                // as the first argument is
                // less than, equal to, or greater than the second.
                int maxWindowOfT1 = tasks.get(treatments.get(treatmentID1).getTaskID()).getMaxWindow();
                int maxWindowOfT2 = tasks.get(treatments.get(treatmentID2).getTaskID()).getMaxWindow();
                return Integer.compare(maxWindowOfT1, maxWindowOfT2);
            }).collect(Collectors.toCollection(LinkedList::new));
            System.out.println("treatmentIDSortedByMaxWindow: " + treatmentIDSortedByMaxWindow);
            System.out.println("processing tasks");
            System.out.println("timeRemaining at the top of the hour: " + timeRemaining);
            for (int i = 0; i < treatmentIDSortedByMaxWindow.size(); i++) {
                // is this task overdue? task may be impossible to do.
                int startHour = treatments.get(treatmentIDSortedByMaxWindow.get(i)).getStartHour();
                int maxWindow = tasks.get(treatments.get(treatmentIDSortedByMaxWindow.get(i)).getTaskID()).getMaxWindow() - 1;
                if (currentHour > startHour + maxWindow) {
                    scheduleString.append(" [+ backup volunteer]");
                    scheduleString.append("\n");
                    System.out.println("Overdue!"); // TODO handle the overdue. Impossible? or Call for back up?
                    runningTreatmentTodosIDs.removeFirstOccurrence(treatmentIDSortedByMaxWindow.get(i));
                    continue;
                }


                // can this finish in time?
                int duration = tasks.get(treatments.get(treatmentIDSortedByMaxWindow.get(i)).getTaskID()).getDuration();
                if (duration <= timeRemaining) {
                    // if yes, process the task.
                    System.out.println("working on treatment id, task id: "
                            + treatmentIDSortedByMaxWindow.get(i)
                            + ", "
                            + treatments.get(treatmentIDSortedByMaxWindow.get(i)).getTaskID());
                    // subtract from timeRemaining in the hour
                    timeRemaining -= duration;
                    scheduleString.append("* " + tasks.get(treatments.get(treatmentIDSortedByMaxWindow.get(i)).getTaskID()).getDescription()
                                            + " (" + animals.get(treatments.get(treatmentIDSortedByMaxWindow.get(i)).getAnimalID()).getAnimalNickname() + ")");
                    scheduleString.append("\n");
                    System.out.println("timeRemaining: " + timeRemaining);
                    // and remove it from the list
                    runningTreatmentTodosIDs.removeFirstOccurrence(treatmentIDSortedByMaxWindow.get(i));

                }
            }
            System.out.println("timeRemaining at the end of the hour: " + timeRemaining);

            currentHour++;
            System.out.println();
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
    public static StringBuilder getData() {
        return scheduleString;
    }
}