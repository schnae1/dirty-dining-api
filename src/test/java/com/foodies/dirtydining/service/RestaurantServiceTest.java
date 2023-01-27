package com.foodies.dirtydining.service;

import com.foodies.dirtydining.dao.IRestaurantRepository;
import com.foodies.dirtydining.dto.RestaurantResponse;
import com.foodies.dirtydining.dto.RestaurantsResponse;
import com.foodies.dirtydining.model.Restaurant;
import com.foodies.dirtydining.utils.Utils;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class RestaurantServiceTest {

    @Tested
    private RestaurantService restaurantService;

    @Injectable
    private IRestaurantRepository restaurantRepository;

    private final Utils utils = new Utils();

    @Injectable
    private ModelMapper modelMapper;

    @Test
    void getRestaurantById() throws IOException {

        final String json = new String(Files.readAllBytes(Paths.get("src/test/java/com/foodies/dirtydining/resources/restaurantModel.json")));
        final Restaurant restaurantModel = utils.mapFromJson(json, Restaurant.class);
        final String jsonDto = new String(Files.readAllBytes(Paths.get("src/test/java/com/foodies/dirtydining/resources/restaurantDto.json")));
        final com.foodies.dirtydining.dto.Restaurant restaurantDto = utils.mapFromJson(jsonDto, com.foodies.dirtydining.dto.Restaurant.class);
        new Expectations() {{
            restaurantRepository.getRestaurantById(anyString);
            result = restaurantModel;
            modelMapper.map(any, (Class) any);
            result = restaurantDto;
        }};

        final String id = "63b35b75c521c97fbc838bc6";
        final RestaurantResponse restaurantResponse = restaurantService.getRestaurantById(id);

        assertNotNull(restaurantResponse);
        assertNotNull(restaurantResponse.getRestaurant());
        assertEquals(restaurantResponse.getRestaurant().getRestaurantName(), "MCDONALD'S #30169");
        assertEquals(restaurantResponse.getRestaurant().getId(), id);
    }

    @Test
    void searchRestaurants() throws IOException {

        final String json = new String(Files.readAllBytes(Paths.get("src/test/java/com/foodies/dirtydining/resources/restaurantModel.json")));
        final Restaurant restaurant = utils.mapFromJson(json, Restaurant.class);
        final Page<Restaurant> restaurantsModel = new PageImpl<>(new ArrayList<>(Collections.singletonList(restaurant)));

        final String jsonDto = new String(Files.readAllBytes(Paths.get("src/test/java/com/foodies/dirtydining/resources/restaurantDto.json")));
        final com.foodies.dirtydining.dto.Restaurant restaurantDto = utils.mapFromJson(jsonDto, com.foodies.dirtydining.dto.Restaurant.class);

        new Expectations() {{
            restaurantRepository.searchRestaurants(anyString, anyInt, anyInt);
            result = restaurantsModel;

            modelMapper.map(any, (Class) any);
            result = restaurantDto;
        }};

        final String input = "mcdonalds";
        final Integer page = 0;
        final Integer size = 10;
        final RestaurantsResponse restaurantsResponse = restaurantService.searchRestaurants(input, page, size);

        assertNotNull(restaurantsResponse);
        assertNotNull(restaurantsResponse.getRestaurants());
        assertEquals(restaurantsResponse.getRestaurants().get(0).getRestaurantName(), "MCDONALD'S #30169");
        assertEquals(restaurantsResponse.getRestaurants().get(0).getId(), "63b35b75c521c97fbc838bc6");
    }
}