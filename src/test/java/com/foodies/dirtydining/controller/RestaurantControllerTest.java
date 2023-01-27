package com.foodies.dirtydining.controller;

import com.foodies.dirtydining.dto.RestaurantResponse;
import com.foodies.dirtydining.dto.RestaurantsResponse;
import com.foodies.dirtydining.service.RestaurantService;
import com.foodies.dirtydining.utils.Utils;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RestaurantControllerTest {

    @Injectable
    private RestaurantService restaurantService;

    @Tested
    private RestaurantController restaurantController;

    private final Utils utils = new Utils();

    @Test
    public void searchRestaurantsSuccess() throws Exception {

        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final String json = new String(Files.readAllBytes(Paths.get("src/test/java/com/foodies/dirtydining/resources/restaurantsResponse.json")));
        final RestaurantsResponse response = utils.mapFromJson(json, RestaurantsResponse.class);

        new Expectations() {{
            restaurantService.searchRestaurants(anyString, anyInt, anyInt);
            result = response;
        }};

        final String query = "churchs chicken 4851 charleston";
        final Integer page = 0;
        final Integer size = 10;
        final RestaurantsResponse restaurantsResponse = restaurantController.searchRestaurants(query, page, size);

        assertTrue(restaurantsResponse.getRestaurants().size() > 0);
        assertNotNull(restaurantsResponse.getRestaurants().get(0).getRestaurantName());
        assertNotNull(restaurantsResponse.getRestaurants().get(0).getScore());
        assertTrue(restaurantsResponse.getSize() > 0);
        assertTrue(restaurantsResponse.getTotalPages() > 0);
        assertTrue(restaurantsResponse.getTotalResults() > 0);
    }

    @Test
    public void searchRestaurantsFailure() throws Exception {

        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        assertThrows(MissingServletRequestParameterException.class, () -> restaurantController.searchRestaurants(null, 0, 10));
    }

    @Test
    public void getRestaurantByIdSuccess() throws Exception {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final String json = new String(Files.readAllBytes(Paths.get("src/test/java/com/foodies/dirtydining/resources/restaurantResponse.json")));
        final RestaurantResponse response = utils.mapFromJson(json, RestaurantResponse.class);

        new Expectations() {{
            restaurantService.getRestaurantById(anyString);
            result = response;
        }};

        final String id = "63b35b75c521c97fbc838bc6";
        final RestaurantResponse restaurantResponse = restaurantController.getRestaurantById(id);

        assertNotNull(restaurantResponse);
        assertNotNull(restaurantResponse.getRestaurant());
        assertNotNull(restaurantResponse.getRestaurant().getId());
        assertNotNull(restaurantResponse.getRestaurant().getRestaurantName());
    }
}
