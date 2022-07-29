package com.foodies.dirtydiningapi.dao;

import com.foodies.dirtydiningapi.model.Restaurant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantRowMapper implements RowMapper<Restaurant> {

    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {

        Restaurant restaurant = new Restaurant(
                rs.getString("permit_number"),
                rs.getString("restaurant_name"),
                rs.getString("address"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude"),
                rs.getInt("city_id"),
                rs.getString("city_name"),
                rs.getString("zip_code"),
                rs.getString("search_text"),
                rs.getDouble("distance"),
                "Nevada"
        );

        return restaurant;
    }

}
