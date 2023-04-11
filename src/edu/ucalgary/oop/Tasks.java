package edu.ucalgary.oop;

public class Tasks {
    private int taskID;
    private String description;
    private int duration;
    private int maxWindow;
    private int feedingDuration;
    private int prepDuration;


    public Tasks(int taskID, String description, int duration, int maxWindow) {
        this.taskID = taskID;
        this.description = description;
        this.duration = duration;
        this.maxWindow = maxWindow;
    }

    public Tasks() {

    }

    public int getTaskID() {
        return taskID;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getMaxWindow() {
        return maxWindow;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", description=" + description +
                ", duration=" + duration +
                ", maxWindow=" + maxWindow +
                '}';
    }
}
