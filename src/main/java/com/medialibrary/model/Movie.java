package com.medialibrary.model;

import java.time.LocalDate;

public class Movie extends MediaItem{

    public Movie(){};

    public Movie(String title, String creator, int year, int totalValue){
        super(title, creator, year, totalValue);
    }

    @Override
    public void updateProgress(int newValue) {
        if (newValue < 0){
            return;
        }
        currentValue = Math.min(newValue, totalValue);
        if (currentValue == totalValue) {
            dateCompleted = LocalDate.now();
            status = MediaStatus.COMPLETED;
        }
        else if (currentValue > 0){
            status = MediaStatus.IN_PROGRESS;
        }
        else {
            status = MediaStatus.PLANNED;
        }
    }

    @Override
    public double calculatePercentage(){
        if (totalValue == 0) {
            return 0;
        }
        return (double) currentValue / totalValue * 100;
    }

    @Override
    public String getProgressDisplay() {
        return "Просмотрено " + currentValue + " минут из " + totalValue + " (" + calculatePercentage() + "%)";
    }
}
