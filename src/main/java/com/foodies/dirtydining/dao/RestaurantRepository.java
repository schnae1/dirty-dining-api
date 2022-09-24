package com.foodies.dirtydining.dao;

import com.foodies.dirtydining.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;


@Component
public class RestaurantRepository implements IRestaurantRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Restaurant> findRestaurantsNearBy(final double latitude, final double longitude) {
        return null;
    }

    @Override
    public void insertRestaurants(final List<Restaurant> restaurants) {
        mongoTemplate.insertAll(restaurants);
        Set<String> names = mongoTemplate.getCollectionNames();
        System.out.println("test");
    }
}
