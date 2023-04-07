package edu.ucalgary.oop;

public class Feeding {
    private String animalNickname;
    private Species animalSpecies;
    private int prepTime;
    private int feedingTime;
    private int startHour;
    private int maxWindow;

    public Feeding(String animalNickname, Species animalSpecies, int prepTime, int feedingTime, int startHour, int maxWindow) {
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
        this.prepTime = prepTime;
        this.feedingTime = feedingTime;
        this.startHour = startHour;
        this.maxWindow = maxWindow;
    }

    public String getAnimalNickname() {
        return animalNickname;
    }

    public void setAnimalNickname(String animalNickname) {
        this.animalNickname = animalNickname;
    }

    public Species getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(Species animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(int feedingTime) {
        this.feedingTime = feedingTime;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getMaxWindow() {
        return maxWindow;
    }

    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }

    @Override
    public String toString() {
        return "Feeding{" +
                "animalNickname='" + animalNickname + '\'' +
                ", animalSpecies=" + animalSpecies +
                ", prepTime=" + prepTime +
                ", feedingTime=" + feedingTime +
                ", startHour=" + startHour +
                ", maxWindow=" + maxWindow +
                '}';
    }
}
