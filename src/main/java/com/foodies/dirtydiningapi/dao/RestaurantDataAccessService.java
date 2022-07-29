package com.foodies.dirtydiningapi.dao;

import com.foodies.dirtydiningapi.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestaurantDataAccessService implements RestaurantDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getNearByRestaurants(double longitude, double latitude, int distance, int offset, int row_count) {

        List<Restaurant> restaurants = jdbcTemplate.query(
                SQL_GET_NEAREST_RESTAURANTS,
                new Object[]{latitude, longitude, latitude, distance, offset, row_count},
                new RestaurantRowMapper());

        List<Restaurant> results = jdbcTemplate.query(
                SQL_GET_RESULTS_COUNT,
                new Object[]{latitude, longitude, latitude, distance},
                new RestaurantRowMapper());
        Integer resultCount = new Integer(results.size());

        Map<String, Object> response = new HashMap<String, Object>();

        response.put("restaurants", restaurants);
        response.put("totalNumOfResults", resultCount);

        return response;

    }

}
