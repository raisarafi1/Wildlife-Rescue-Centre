/**
 @authors Raisa Rafi, Rida Khan, Mohamed Ebdalla, Joshua Debele
 @version 7.8
 @since 1.0
 */

package edu.ucalgary.oop;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * The Scheduler class has only two methods. One of the methods is the schedule method which takes in the three
 * hashmaps of information and uses
 */
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
                    EventQueue.invokeLater(() -> {
                        new BackupVolunteerGUI().setVisible(true);
                    });

                    scheduleString.append("\n");
                    // calls gui
                    // second gui
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
    
    public static StringBuilder getData() {
        return scheduleString;
    }
}