package com.foodies.dirtydining.util;

import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.foodies.dirtydining.dao.IRestaurantRepository.*;

@Component
public class Data {

    private final Logger logger = LoggerFactory.getLogger(Data.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${restaurant.data.url}")
    private String restaurantDataUrl;

    private static final String ZIP_FILE_PATH = "src/main/resources/data/restaurants.zip";
    private static final String DESTINATION_PATH = "src/main/resources/data/";

    public void downloadData() {

        logger.info("Downloading zipped restaurant data...");

        try {
            FileUtils.copyURLToFile(new URL(restaurantDataUrl), new File(ZIP_FILE_PATH), 30000, 30000);
            logger.info("Download complete.");
        } catch (Exception ex) {
            logger.error("Error while downloading zipped restaurant data.", ex);
        }
    }

    public void extractZippedFile() {
        logger.info("Extracting zipped files...");

        try {
            new ZipFile(ZIP_FILE_PATH).extractAll(DESTINATION_PATH);
            logger.info("Extracted files successfully.");
        } catch (Exception ex) {
            logger.error("Error while extracting zipped filed.", ex);
        }
    }

    public void populateDB() {

        System.out.println("Populating DB...");

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        List<Object[]> restaurantList = new ArrayList<>();

        // Get restaurant data from csv file
        try{

            br = new BufferedReader(new FileReader(RESTAURANTS_FILE_PATH));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                String[] restaurantData = line.split(cvsSplitBy);

                int categoryId = Integer.parseInt(restaurantData[3].trim());

                if(!restaurantData[4].trim().toLowerCase().contains("deleted")
                        && !restaurantData[4].trim().toLowerCase().contains("dishwash")
                        && !restaurantData[4].trim().toLowerCase().contains("prep")
                        && !restaurantData[4].trim().toLowerCase().contains("dishroom")
                        && !restaurantData[4].trim().toLowerCase().contains("support kitchen")
                        && !restaurantData[4].trim().toLowerCase().contains("main kitchen")
                        && !restaurantData[4].trim().toLowerCase().contains("employee")
                        && !restaurantData[4].trim().toLowerCase().contains("room service")
                        && !restaurantData[4].trim().toLowerCase().contains("service bar")
                        && !restaurantData[4].trim().toLowerCase().contains("drink station")
                        && !restaurantData[4].trim().toLowerCase().contains("processing")
                        && !restaurantData[4].trim().toLowerCase().contains("catering")
                        && !restaurantData[4].trim().toLowerCase().contains("commissary")
                        && !restaurantData[4].trim().toLowerCase().contains("concession")
                        && !restaurantData[4].trim().toLowerCase().contains("storage")
                        && !restaurantData[4].trim().toLowerCase().contains("svc bar")
                        && !restaurantData[4].trim().toLowerCase().contains("7-eleven")
                        && !restaurantData[4].trim().toLowerCase().contains("circle k")
                        && !restaurantData[4].trim().toLowerCase().contains("produce")
                        && !restaurantData[4].trim().toLowerCase().contains("rebel #")
                        && !restaurantData[4].trim().toLowerCase().contains("front line")
                        && !restaurantData[4].trim().toLowerCase().contains("holding kitchen")
                        && !restaurantData[4].trim().toLowerCase().contains("snack")
                        && !restaurantData[4].trim().toLowerCase().contains("elem school")
                        && !restaurantData[4].trim().toLowerCase().contains("elementary school")
                        && !restaurantData[4].trim().toLowerCase().contains("wal mart")
                        && !restaurantData[4].trim().toLowerCase().contains("ullom")
                        && !restaurantData[4].trim().toLowerCase().contains("albertsons")
                        && !restaurantData[4].trim().toLowerCase().contains("cold line")
                        && !restaurantData[4].trim().toLowerCase().contains("expo line")
                        && !restaurantData[4].trim().toLowerCase().contains("mcdonalds")
                        && !restaurantData[4].trim().toLowerCase().contains("mcdonald's")
                        && !restaurantData[4].trim().toLowerCase().contains("burger king")
                        && !restaurantData[4].trim().toLowerCase().contains("taco bell")
                        && !restaurantData[4].trim().toLowerCase().contains("kentucky fried chicken")
                        && !restaurantData[4].trim().toLowerCase().contains("el pollo loco")
                        && !restaurantData[4].trim().toLowerCase().contains("carl's jr")
                        && !restaurantData[4].trim().toLowerCase().contains("del taco")
                        && !restaurantData[4].trim().toLowerCase().contains("panda express")
                        && !restaurantData[4].trim().toLowerCase().contains("subway")
                        && !restaurantData[4].trim().toLowerCase().contains("little caesars")
                        && !restaurantData[4].trim().toLowerCase().contains("sonic drive")
                        && !restaurantData[4].trim().toLowerCase().contains("kfc")
                        && !restaurantData[4].trim().toLowerCase().contains("dunkin donuts")
                        && !restaurantData[4].trim().toLowerCase().contains("u swirl yogurt")
                        && !restaurantData[4].trim().toLowerCase().contains("pizza hut")
                        && !restaurantData[4].trim().toLowerCase().contains("papa john")
                        && !restaurantData[4].trim().toLowerCase().contains("red lobster")
                        && !restaurantData[4].trim().toLowerCase().contains("jack in the box")
                        && !restaurantData[4].trim().toLowerCase().contains("wendy's")
                        && !restaurantData[4].trim().toLowerCase().contains("arby's")
                        && !restaurantData[4].trim().toLowerCase().contains("schlotzsky's")
                        && !restaurantData[4].trim().toLowerCase().contains("church's chicken")
                        && !restaurantData[4].trim().toLowerCase().contains("farmer boys")
                        && !restaurantData[4].trim().toLowerCase().contains("quizno's")
                        && !restaurantData[4].trim().toLowerCase().contains("jamba juice")
                        && !restaurantData[4].trim().toLowerCase().contains("starbucks")
                        && !restaurantData[4].trim().toLowerCase().contains("auntie anne's pretzels")
                        && !restaurantData[4].trim().toLowerCase().contains("freddy's frozen custard")
                        && !restaurantData[4].trim().toLowerCase().contains("applebee's")
                        && !restaurantData[4].trim().toLowerCase().contains("firehouse subs")
                        && !restaurantData[4].trim().toLowerCase().contains("cold stone creamery")
                        && !restaurantData[4].trim().toLowerCase().contains("popeye's chicken")
                        && !restaurantData[4].trim().toLowerCase().contains("domino's")
                        && !restaurantData[4].trim().toLowerCase().contains("ihop")
                        && !restaurantData[4].trim().toLowerCase().contains("denny's")
                        && !restaurantData[4].trim().toLowerCase().contains("tropical smoothie")
                        && !restaurantData[4].trim().toLowerCase().contains("fatburger")
                        && !restaurantData[4].trim().toLowerCase().contains("baja fresh")
                        && !restaurantData[4].trim().toLowerCase().contains("peter piper pizza")
                        && !restaurantData[4].trim().toLowerCase().contains("long john silver's")
                        && !restaurantData[4].trim().toLowerCase().contains("pt's")
                        && !restaurantData[4].trim().toLowerCase().contains("am/pm")
                        && !restaurantData[4].trim().toLowerCase().contains("tropicana pizza")
                        && !restaurantData[4].trim().toLowerCase().contains("el pollo loco")
                        && !restaurantData[4].trim().toLowerCase().contains("in n out")
                        && !restaurantData[4].trim().toLowerCase().contains("capriotti's sandwich")
                        && !restaurantData[4].trim().toLowerCase().contains("in-n-out")
                        && !restaurantData[4].trim().toLowerCase().contains("sbarro")
                        && !restaurantData[4].trim().toLowerCase().contains("hot dog on a stick")
                        && !restaurantData[4].trim().toLowerCase().contains("raising cane's")
                        && !restaurantData[4].trim().toLowerCase().contains("csn monster cafe")
                        && !restaurantData[4].trim().toLowerCase().contains("coyote cafe")
                        && !restaurantData[4].trim().toLowerCase().contains("csn pizza")
                        && !restaurantData[4].trim().toLowerCase().contains("buffalo wild wings")
                        && !restaurantData[4].trim().toLowerCase().contains("don tortaco")
                        && !restaurantData[4].trim().toLowerCase().contains("roberto's taco")
                        && !restaurantData[4].trim().toLowerCase().contains("tgi fridays")
                        && !restaurantData[4].trim().toLowerCase().contains("outback steakhouse")
                        && !restaurantData[4].trim().toLowerCase().contains("famous dave's")
                        && !restaurantData[4].trim().toLowerCase().contains("chili's grill")
                        && !restaurantData[4].trim().toLowerCase().contains("cheesecake factory")
                        && !restaurantData[4].trim().toLowerCase().contains("bj's restaurant")
                        && !restaurantData[4].trim().toLowerCase().contains("cracker barrel")
                        && !restaurantData[4].trim().toLowerCase().contains("black bear diner")
                        && !restaurantData[4].trim().toLowerCase().contains("marie callender")
                        && !restaurantData[4].trim().toLowerCase().contains("chick-fil-a")
                        && !restaurantData[4].trim().toLowerCase().contains("red robin")
                        && !restaurantData[4].trim().toLowerCase().contains("pf chang")
                        && !restaurantData[4].trim().toLowerCase().contains("tgi fridays")
                        && !restaurantData[4].trim().toLowerCase().contains("california pizza kitchen")
                        && !restaurantData[4].trim().toLowerCase().contains("shake shack")
                        && !restaurantData[4].trim().toLowerCase().contains("jimmy john's")
                        && !restaurantData[4].trim().toLowerCase().contains("port of subs")
                        && !restaurantData[4].trim().toLowerCase().contains("five guys")
                        && !restaurantData[4].trim().toLowerCase().contains("chipotle mexican grill")
                        && !restaurantData[4].trim().toLowerCase().contains("habit burger")
                        && !restaurantData[4].trim().toLowerCase().contains("tgi fridays")
                        && !restaurantData[4].trim().toLowerCase().contains("mimi's cafe")
                        && !restaurantData[4].trim().toLowerCase().contains("jason's deli")
                        && !restaurantData[4].trim().toLowerCase().contains("olive garden")
                        && !restaurantData[4].trim().toLowerCase().contains("tommy's world Famous")
                        && !restaurantData[4].trim().toLowerCase().contains("wingstop")
                        && !restaurantData[4].trim().toLowerCase().contains("cardenas market")

                        && !restaurantData[20].trim().toLowerCase().contains("deleted")
                        && !restaurantData[20].trim().toLowerCase().contains("dishwash")
                        && !restaurantData[20].trim().toLowerCase().contains("prep")
                        && !restaurantData[20].trim().toLowerCase().contains("dishroom")
                        && !restaurantData[20].trim().toLowerCase().contains("support kitchen")
                        && !restaurantData[20].trim().toLowerCase().contains("main kitchen")
                        && !restaurantData[20].trim().toLowerCase().contains("employee")
                        && !restaurantData[20].trim().toLowerCase().contains("room service")
                        && !restaurantData[20].trim().toLowerCase().contains("service bar")
                        && !restaurantData[20].trim().toLowerCase().contains("drink station")
                        && !restaurantData[20].trim().toLowerCase().contains("processing")
                        && !restaurantData[20].trim().toLowerCase().contains("catering")
                        && !restaurantData[20].trim().toLowerCase().contains("commissary")
                        && !restaurantData[20].trim().toLowerCase().contains("concession")
                        && !restaurantData[20].trim().toLowerCase().contains("storage")
                        && !restaurantData[20].trim().toLowerCase().contains("svc bar")
                        && !restaurantData[20].trim().toLowerCase().contains("7-eleven")
                        && !restaurantData[20].trim().toLowerCase().contains("circle k")
                        && !restaurantData[20].trim().toLowerCase().contains("produce")
                        && !restaurantData[20].trim().toLowerCase().contains("rebel #")
                        && !restaurantData[20].trim().toLowerCase().contains("front line")
                        && !restaurantData[20].trim().toLowerCase().contains("holding kitchen")
                        && !restaurantData[20].trim().toLowerCase().contains("snack")
                        && !restaurantData[20].trim().toLowerCase().contains("elem school")
                        && !restaurantData[20].trim().toLowerCase().contains("elementary school")
                        && !restaurantData[20].trim().toLowerCase().contains("wal mart")
                        && !restaurantData[20].trim().toLowerCase().contains("ullom")
                        && !restaurantData[20].trim().toLowerCase().contains("albertsons")
                        && !restaurantData[20].trim().toLowerCase().contains("cold line")
                        && !restaurantData[20].trim().toLowerCase().contains("expo line")
                        && !restaurantData[20].trim().toLowerCase().contains("mcdonalds")
                        && !restaurantData[20].trim().toLowerCase().contains("mcdonald's")
                        && !restaurantData[20].trim().toLowerCase().contains("burger king")
                        && !restaurantData[20].trim().toLowerCase().contains("taco bell")
                        && !restaurantData[20].trim().toLowerCase().contains("kentucky fried chicken")
                        && !restaurantData[20].trim().toLowerCase().contains("el pollo loco")
                        && !restaurantData[20].trim().toLowerCase().contains("carl's jr")
                        && !restaurantData[20].trim().toLowerCase().contains("del taco")
                        && !restaurantData[20].trim().toLowerCase().contains("panda express")
                        && !restaurantData[20].trim().toLowerCase().contains("subway")
                        && !restaurantData[20].trim().toLowerCase().contains("little caesars")
                        && !restaurantData[20].trim().toLowerCase().contains("sonic drive")
                        && !restaurantData[20].trim().toLowerCase().contains("kfc")
                        && !restaurantData[20].trim().toLowerCase().contains("dunkin donuts")
                        && !restaurantData[20].trim().toLowerCase().contains("u swirl yogurt")
                        && !restaurantData[20].trim().toLowerCase().contains("pizza hut")
                        && !restaurantData[20].trim().toLowerCase().contains("papa john")
                        && !restaurantData[20].trim().toLowerCase().contains("red lobster")
                        && !restaurantData[20].trim().toLowerCase().contains("jack in the box")
                        && !restaurantData[20].trim().toLowerCase().contains("wendy's")
                        && !restaurantData[20].trim().toLowerCase().contains("arby's")
                        && !restaurantData[20].trim().toLowerCase().contains("schlotzsky's")
                        && !restaurantData[20].trim().toLowerCase().contains("church's chicken")
                        && !restaurantData[20].trim().toLowerCase().contains("farmer boys")
                        && !restaurantData[20].trim().toLowerCase().contains("quizno's")
                        && !restaurantData[20].trim().toLowerCase().contains("jamba juice")
                        && !restaurantData[20].trim().toLowerCase().contains("starbucks")
                        && !restaurantData[20].trim().toLowerCase().contains("starbucks")
                        && !restaurantData[20].trim().toLowerCase().contains("auntie anne's pretzels")
                        && !restaurantData[20].trim().toLowerCase().contains("freddy's frozen custard")
                        && !restaurantData[20].trim().toLowerCase().contains("applebee's")
                        && !restaurantData[20].trim().toLowerCase().contains("firehouse subs")
                        && !restaurantData[20].trim().toLowerCase().contains("cold stone creamery")
                        && !restaurantData[20].trim().toLowerCase().contains("popeye's chicken")
                        && !restaurantData[20].trim().toLowerCase().contains("domino's")
                        && !restaurantData[20].trim().toLowerCase().contains("ihop")
                        && !restaurantData[20].trim().toLowerCase().contains("denny's")
                        && !restaurantData[20].trim().toLowerCase().contains("tropical smoothie")
                        && !restaurantData[20].trim().toLowerCase().contains("fatburger")
                        && !restaurantData[20].trim().toLowerCase().contains("baja fresh")
                        && !restaurantData[20].trim().toLowerCase().contains("peter piper pizza")
                        && !restaurantData[20].trim().toLowerCase().contains("long john silver's")
                        && !restaurantData[20].trim().toLowerCase().contains("pt's")
                        && !restaurantData[20].trim().toLowerCase().contains("am/pm")
                        && !restaurantData[20].trim().toLowerCase().contains("tropicana pizza")
                        && !restaurantData[20].trim().toLowerCase().contains("el pollo loco")
                        && !restaurantData[20].trim().toLowerCase().contains("in n out")
                        && !restaurantData[20].trim().toLowerCase().contains("capriotti's sandwich")
                        && !restaurantData[20].trim().toLowerCase().contains("in-n-out")
                        && !restaurantData[20].trim().toLowerCase().contains("sbarro")
                        && !restaurantData[20].trim().toLowerCase().contains("hot dog on a stick")
                        && !restaurantData[20].trim().toLowerCase().contains("raising cane's")
                        && !restaurantData[20].trim().toLowerCase().contains("csn monster cafe")
                        && !restaurantData[20].trim().toLowerCase().contains("coyote cafe")
                        && !restaurantData[20].trim().toLowerCase().contains("csn pizza")
                        && !restaurantData[20].trim().toLowerCase().contains("buffalo wild wings")
                        && !restaurantData[20].trim().toLowerCase().contains("don tortaco")
                        && !restaurantData[20].trim().toLowerCase().contains("roberto's taco")
                        && !restaurantData[20].trim().toLowerCase().contains("tgi fridays")
                        && !restaurantData[20].trim().toLowerCase().contains("outback steakhouse")
                        && !restaurantData[20].trim().toLowerCase().contains("famous dave's")
                        && !restaurantData[20].trim().toLowerCase().contains("chili's grill")
                        && !restaurantData[20].trim().toLowerCase().contains("cheesecake factory")
                        && !restaurantData[20].trim().toLowerCase().contains("bj's restaurant")
                        && !restaurantData[20].trim().toLowerCase().contains("cracker barrel")
                        && !restaurantData[20].trim().toLowerCase().contains("black bear diner")
                        && !restaurantData[20].trim().toLowerCase().contains("marie callender")
                        && !restaurantData[20].trim().toLowerCase().contains("chick-fil-a")
                        && !restaurantData[20].trim().toLowerCase().contains("red robin")
                        && !restaurantData[20].trim().toLowerCase().contains("pf chang")
                        && !restaurantData[20].trim().toLowerCase().contains("tgi fridays")
                        && !restaurantData[20].trim().toLowerCase().contains("california pizza kitchen")
                        && !restaurantData[20].trim().toLowerCase().contains("shake shack")
                        && !restaurantData[20].trim().toLowerCase().contains("jimmy john's")
                        && !restaurantData[20].trim().toLowerCase().contains("port of subs")
                        && !restaurantData[20].trim().toLowerCase().contains("five guys")
                        && !restaurantData[20].trim().toLowerCase().contains("chipotle mexican grill")
                        && !restaurantData[20].trim().toLowerCase().contains("habit burger")
                        && !restaurantData[20].trim().toLowerCase().contains("tgi fridays")
                        && !restaurantData[20].trim().toLowerCase().contains("mimi's cafe")
                        && !restaurantData[20].trim().toLowerCase().contains("jason's deli")
                        && !restaurantData[20].trim().toLowerCase().contains("olive garden")
                        && !restaurantData[20].trim().toLowerCase().contains("tommy's world Famous")
                        && !restaurantData[20].trim().toLowerCase().contains("wingstop")
                        && !restaurantData[20].trim().toLowerCase().contains("cardenas market")) {

                    if(categoryId >= 1003 && categoryId <= 1008) {
                        restaurantList.add(createRestaurantData(restaurantData));
                    }

                }

            }

            System.out.println(restaurantList.size());
            for (int i = 0; i < restaurantList.size(); i += BATCH_SIZE) {

                List<Object[]> batchList = restaurantList.subList(i, Math.min(i + BATCH_SIZE, restaurantList.size()));
                jdbcTemplate.batchUpdate(SQL_INSERT_RESTAURANT_DATA, batchList);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object[] createRestaurantData(String[] restaurantData) {

        Object[] obj = new Object[]{};

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

            obj = new Object[]{
                    permitNumber,
                    restaurantName,
                    address,
                    latitude,
                    longitude,
                    cityId,
                    cityName,
                    zipCode,
                    searchText
            };

        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

}
