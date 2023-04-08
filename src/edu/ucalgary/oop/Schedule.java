package edu.ucalgary.oop;

public class Schedule {
    private int treatmentID;
    private int currentHour;
    private String taskDescription;
    private String animalNickname;
//    private int numOfAnimalsFed;

    public Schedule(int treatmentID, int currentHour, String taskDescription, String animalNickname) {
        this.treatmentID = treatmentID;
        this.currentHour = currentHour;
        this.taskDescription = taskDescription;
        this.animalNickname = animalNickname;
//        this.numOfAnimalsFed = numOfAnimalsFed;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getAnimalNickname() {
        return animalNickname;
    }

    public void setAnimalNickname(String animalNickname) {
        this.animalNickname = animalNickname;
    }

//    public int getNumOfAnimalsFed() {
//        return numOfAnimalsFed;
//    }
//
//    public void setNumOfAnimalsFed(int numOfAnimalsFed) {
//        this.numOfAnimalsFed = numOfAnimalsFed;
//    }


    @Override
    public String toString() {
        return "Schedule{" +
                "treatmentID=" + treatmentID +
                ", currentHour=" + currentHour +
                ", taskDescription='" + taskDescription + '\'' +
                ", animalNickname='" + animalNickname + '\'' +
                '}';
    }
}
