package com.foodies.dirtydining.service;

import com.foodies.dirtydining.dao.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RestaurantService {

    @Autowired
    private IRestaurantRepository IRestaurantRepository;

    public Map<String, Object> getNearRestaurants(double longitude, double latitude, int distance, int offset, int row_count) {

        return IRestaurantRepository.getNearByRestaurants(longitude, latitude, distance, offset, row_count);

    }

}