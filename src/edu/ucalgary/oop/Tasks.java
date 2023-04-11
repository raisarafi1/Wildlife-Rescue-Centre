package edu.ucalgary.oop;

public class Tasks {
    private int taskID;
    private String description;
    private int duration;
    private int maxWindow;


    public Tasks(int taskID, String description, int duration, int maxWindow) throws IllegalArgumentException {
        if(duration < 5 || duration > 60) {
            throw new IllegalArgumentException("The duration is either too short or too long to be completed within 60 minutes");
        }
        
        if(maxWindow < 1 || maxWindow > 6) {
            throw new IllegalArgumentException("The max window does not seem reasonable for task");
        }

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
