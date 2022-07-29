package com.foodies.dirtydiningapi.dao;

import java.util.Map;

public interface RestaurantDao {

    String RESTAURANTS_FILE_PATH = "/home/es/rest/restaurant_establishments.csv";
    int BATCH_SIZE = 50;
    String SQL_INSERT_RESTAURANT_DATA = "insert into restaurant_establishments \n" +
            "(permit_number,\n" +
            "restaurant_name,\n" +
            "address,\n" +
            "latitude,\n" +
            "longitude,\n" +
            "city_id,\n" +
            "city_name,\n" +
            "zip_code,\n" +
            "search_text)\n" +
            "VALUES(?,?,?,?,?,?,?,?,?)";
    String SQL_GET_NEAREST_RESTAURANTS = "SELECT permit_number, restaurant_name, address, latitude,\n" +
            "longitude, city_id, city_name, zip_code, search_text, ( 3959 * acos( cos( radians(?) ) * cos( radians( latitude ) ) \n" +
            "    * cos( radians( longitude ) - radians(?) ) + sin( radians(?) ) * sin(radians(latitude)) ) ) AS distance \n" +
            "FROM restaurant_establishments\n" +
            "HAVING distance < ?\n" +
            "ORDER BY distance\n" +
            "LIMIT ?, ?";
    String SQL_GET_RESULTS_COUNT = "SELECT permit_number, restaurant_name, address, latitude,\n" +
            "longitude, city_id, city_name, zip_code, search_text, ( 3959 * acos( cos( radians(?) ) * cos( radians( latitude ) ) \n" +
            "    * cos( radians( longitude ) - radians(?) ) + sin( radians(?) ) * sin(radians(latitude)) ) ) AS distance \n" +
            "FROM restaurant_establishments\n" +
            "HAVING distance < ?\n" +
            "ORDER BY distance";
    Map<String, Object> getNearByRestaurants(double longitude, double latitude, int distance, int offset, int row_count);

}
