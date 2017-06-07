package com.example.wakeuplabs.freefuud;

/**
 * Created by Anjana on 6/7/2017.
 */

public class DataProvider {
    private String events;
    private String food;
    private String location;
    private String time;

    public void setEvents(String events) {
        this.events = events;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvents() {

        return events;
    }

    public String getFood() {
        return food;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }


    public DataProvider(String events, String food, String location, String time){
        this.events = events;
        this.food = food;
        this.location = location;
        this.time = time;
    }

}
