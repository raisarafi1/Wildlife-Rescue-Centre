/**
 @authors Raisa Rafi, Rida Khan, Mohamed Ebdalla, Joshua Debele
 @version 4.6
 @since 1.0
 */


package edu.ucalgary.oop;

/**
 The Tasks class implements a task object and assigns attributes to each task.
 */


public class Tasks {
    private int taskID;
    private String description;
    private int duration;
    private int maxWindow;

    /**
     *
     * @param taskID
     * @param description
     * @param duration
     * @param maxWindow
     * @throws IllegalArgumentException
     */
    public Tasks(int taskID, String description, int duration, int maxWindow) throws IllegalArgumentException {
        this.taskID = taskID;
        this.description = description;
        this.duration = duration;
        this.maxWindow = maxWindow;
    }

    public Tasks() {

    }


    /**
     * getTaskID()
     * @return taskID
     */
    public int getTaskID() {
        return taskID;
    }
    /**
     * getDescription()
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * getDuration()
     * @return Duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * getMaxWindow()
     * @return maxWindow
     */
    public int getMaxWindow() {
        return maxWindow;
    }

    /**
     * setTaskID(), sets @param taskID as taskID
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * @param description
     * sets description to the parameter passed in
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param duration
     * sets duration to the parameter passed in
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    /**
     * @param maxWindow
     * sets maxWindow to the parameter passed in
     */
    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }

    /**
     *
     * @return a concatenated string of taskID, description, duration and maxWindow
     */
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
