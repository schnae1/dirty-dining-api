package com.foodies.dirtydining.dto;

public class Restaurant {

    private String id;
    private String permitNumber;
    private String restaurantName;
    private String address;
    private String cityName;
    private String zipCode;
    private String currentGrade;
    private String currentDemerits;
    private String dateCurrent;
    private String previousGrade;
    private String datePrevious;
    private Float score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(String currentGrade) {
        this.currentGrade = currentGrade;
    }

    public String getCurrentDemerits() {
        return currentDemerits;
    }

    public void setCurrentDemerits(String currentDemerits) {
        this.currentDemerits = currentDemerits;
    }

    public String getDateCurrent() {
        return dateCurrent;
    }

    public void setDateCurrent(String dateCurrent) {
        this.dateCurrent = dateCurrent;
    }

    public String getPreviousGrade() {
        return previousGrade;
    }

    public void setPreviousGrade(String previousGrade) {
        this.previousGrade = previousGrade;
    }

    public String getDatePrevious() {
        return datePrevious;
    }

    public void setDatePrevious(String datePrevious) {
        this.datePrevious = datePrevious;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
