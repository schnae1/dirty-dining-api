package com.foodies.dirtydining.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document
public class Restaurant {

    @Id
    private String id;
    private String permitNumber;
    @TextIndexed
    private String restaurantName;
    @TextIndexed
    private String address;
    private double latitude;
    private double longitude;
    private int    cityId;
    @TextIndexed
    private String cityName;
    @TextIndexed
    private String zipCode;
    @TextIndexed
    private String searchText;
    private String currentGrade;
    private String currentDemerits;
    private String dateCurrent;
    private String previousGrade;
    private String datePrevious;
    @TextScore
    private Float score;

    public Restaurant(String permitNumber, String restaurantName, String address, double latitude, double longitude, int cityId, String cityName, String zipCode, String searchText, String currentGrade, String currentDemerits, String dateCurrent, String previousGrade, String datePrevious) {
        this.permitNumber = permitNumber;
        this.restaurantName = restaurantName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityId = cityId;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.searchText = searchText;
        this.currentGrade = currentGrade;
        this.currentDemerits = currentDemerits;
        this.dateCurrent = dateCurrent;
        this.previousGrade = previousGrade;
        this.datePrevious = datePrevious;
    }

    public String getId() {
        return id;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getSearchText() {
        return searchText;
    }

    public String getCurrentGrade() {
        return currentGrade;
    }

    public String getCurrentDemerits() {
        return currentDemerits;
    }

    public String getDateCurrent() {
        return dateCurrent;
    }

    public String getPreviousGrade() {
        return previousGrade;
    }

    public String getDatePrevious() {
        return datePrevious;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", permitNumber='" + permitNumber + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", searchText='" + searchText + '\'' +
                ", currentGrade='" + currentGrade + '\'' +
                ", currentDemerits='" + currentDemerits + '\'' +
                ", dateCurrent='" + dateCurrent + '\'' +
                ", previousGrade='" + previousGrade + '\'' +
                ", datePrevious='" + datePrevious + '\'' +
                '}';
    }
}
