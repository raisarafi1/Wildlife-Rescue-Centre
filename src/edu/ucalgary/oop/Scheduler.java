package edu.ucalgary.oop;

import java.util.HashMap;
import java.util.LinkedList;

public class Scheduler {

    public static void schedule(HashMap<Integer, Treatments> treatments, HashMap<Integer, Tasks> tasks, HashMap<Integer, Animal> animals) {

//        for (int i = 1; i < treatments.size(); i++) {
//            System.out.println(animals.get(treatments.get(i).getAnimalID()).getAnimalNickname());
//
////            treatments.get(i).getAnimalID() == animals.get(j).getAnimalID();
//            break;
//        }
        System.out.println(treatments);
        System.out.println(tasks);

        LinkedList<Integer> availableTasksByID = new LinkedList<>();
        int currentTime = 0;

        for (int i = 1; i < treatments.size(); i++) {
            if (currentTime == 0 && treatments.get(i).getStartHour() == 0) {
                // TODO can't do this because you need to store more than just the task id
                availableTasksByID.add(treatments.get(i).getTaskID());
                System.out.println(availableTasksByID);

//                System.out.println("the treatment " + tasks.get(treatments.get(i).getTaskID()).getDescription() + " is due at 12am");
            }
//            System.out.println(animals.get(treatments.get(i).getAnimalID()).getAnimalNickname());
//            break;
        }

    }
}
