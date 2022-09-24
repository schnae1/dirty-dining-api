package com.foodies.dirtydining.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("restaurants")
public class Restaurant {

    @Id
    private String id;
    private String permitNumber;
    private String restaurantName;
    private String address;
    private double latitude;
    private double longitude;
    private int    cityId;
    private String cityName;
    private String zipCode;
    private String searchText;
    /*private double distance;
    private String state;*/

    public Restaurant(
            String permitNumber,
            String restaurantName,
            String address,
            double latitude,
            double longitude,
            int cityId,
            String cityName,
            String zipCode,
            String searchText/*,
            double distance,
            String state*/
        ) {
        this.permitNumber = permitNumber;
        this.restaurantName = restaurantName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityId = cityId;
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.searchText = searchText;
        /*this.distance = distance;
        this.state = state;*/

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

    /*public double getDistance() {
        return distance;
    }

    public String getState() {
        return state;
    }*/

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
                '}';
    }
}
