package com.medialibrary.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "book"),
        @JsonSubTypes.Type(value = Movie.class, name = "movie")
})
public abstract class MediaItem {
    protected int id;
    protected String title;
    protected String creator;

    protected int year;
    protected int totalValue;
    protected int currentValue;

    protected MediaStatus status;

    protected LocalDate dateAdded;
    protected LocalDate dateCompleted;

    public MediaItem(){};

    public MediaItem(String title, String creator, int year, int totalValue) {
        this.id = 0;
        this.title = title;
        this.creator = creator;
        this.year = year;
        this.totalValue = totalValue;
        this.currentValue = 0;
        this.status = MediaStatus.PLANNED;
        this.dateAdded = LocalDate.now();
        this.dateCompleted = null;
    }

    public abstract void updateProgress (int currentValue);
    public abstract double calculatePercentage();
    public abstract String getProgressDisplay();

    // ГЕТТЕРЫ
    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getCreator() {
        return creator;
    }
    public int getYear() {
        return year;
    }
    public int getTotalValue() {
        return totalValue;
    }
    public int getCurrentValue() {
        return currentValue;
    }
    public MediaStatus getStatus() {
        return status;
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }
    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    // СЕТТЕРЫ
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
