package com.example.mangerversion;

public class Trip {
    private int id ;
    private  String agency ;
    private  String destination ;
    private  String date ;
    private  String time ;
    private  Integer available ;
    private  Integer price ;


    public Trip(int id, String agency, String destination, String date, String time, Integer available, Integer price) {
        this.id = id;
        this.agency = agency;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.available = available;
        this.price = price;
    }

//    public Trip(int id) {
//        this.id = id;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
