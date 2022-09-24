package com.foodies.dirtydining.util;

import com.foodies.dirtydining.dao.IRestaurantRepository;
import com.foodies.dirtydining.model.Restaurant;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class Data {

    private final Logger logger = LoggerFactory.getLogger(Data.class);

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Value("${restaurant.data.url}")
    private String restaurantDataUrl;

    private static final String ZIP_FILE_PATH = "src/main/resources/data/restaurants.zip";
    private static final String DESTINATION_PATH = "src/main/resources/data/";
    private static final String RESTAURANTS_FILE_PATH = "src/main/resources/data/restaurant_establishments.csv";
    private static final String DELIMITER = ";";

    private void downloadData() throws IOException {
        logger.info("Downloading zipped restaurant data...");
        FileUtils.copyURLToFile(new URL(restaurantDataUrl), new File(ZIP_FILE_PATH), 30000, 30000);
        logger.info("Download complete.");
    }

    private void extractZippedFile() throws ZipException {
        logger.info("Extracting zipped files...");
        new ZipFile(ZIP_FILE_PATH).extractAll(DESTINATION_PATH);
        logger.info("Extracted files successfully.");
    }

    // TODO: START HERE
    // Clean up logic and set up logging
    public void populateDB() {
        logger.info("Populating DB...");

        BufferedReader br = null;
        String line = "";
        List<Restaurant> restaurants = new ArrayList<>();

        // Get restaurant data from csv file
        try{

            /*downloadData();
            extractZippedFile();*/

            br = new BufferedReader(new FileReader(RESTAURANTS_FILE_PATH));
            while ((line = br.readLine()) != null) {

                final String[] restaurantData = line.split(DELIMITER);
                final Restaurant restaurant = createRestaurantData(restaurantData);

                restaurants.add(restaurant);

                if (restaurants.size() >= 100) {
                    logger.info("Inserting into db..");
                    restaurantRepository.insertRestaurants(restaurants);
                    logger.info("Done inserting.");
                    restaurants = new ArrayList<>();
                    Thread.sleep(2000);
                }
            }

            if (restaurants.size() > 1) {
                restaurantRepository.insertRestaurants(restaurants);
            }
        logger.info("Finished all records.");
        } catch (Exception e) {
            logger.error("Error while populating database.", e);
        }

    }

    private Restaurant createRestaurantData(String[] restaurantData) {

        Restaurant restaurant = null;

        try{

            String permitNumber = restaurantData[0].trim();
            String restaurantName = restaurantData[4].trim();
            String address = restaurantData[6].trim();
            double latitude = 0;
            double longitude = 0;
            int cityId = 0;
            try {
                latitude = Double.parseDouble(restaurantData[7].trim());
            } catch (Exception e) {

            }
            try {
                longitude = Double.parseDouble(restaurantData[8].trim());
            } catch (Exception e) {

            }
            try {
                cityId = Integer.parseInt(restaurantData[9].trim());
            } catch (Exception e) {

            }
            String cityName = restaurantData[10].trim();
            String zipCode = restaurantData[11].trim();
            String searchText = restaurantData[20].trim();

            restaurant = new Restaurant(
                    permitNumber,
                    restaurantName,
                    address,
                    latitude,
                    longitude,
                    cityId,
                    cityName,
                    zipCode,
                    searchText
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurant;
    }

}
