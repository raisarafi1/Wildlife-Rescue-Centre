package edu.ucalgary.oop;

public class Treatments {
    private int treatmentID;
    private int animalID;
    private int taskID;
    private int startHour;

    public Treatments(int animalID, int taskID, int startHour) {
        this.animalID = animalID;
        this.taskID = taskID;
        this.startHour = startHour;
    }

    public Treatments() {

    }

    public int getTreatmentID() {
        return treatmentID;
    }

    public int getAnimalID() {
        return animalID;
    }

    public int getTaskID() {
        return taskID;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setTreatmentID(int treatmentID) {
        this.treatmentID = treatmentID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "animalID=" + animalID +
                ", taskID=" + taskID +
                ", startHour=" + startHour +
                '}';
    }
}
