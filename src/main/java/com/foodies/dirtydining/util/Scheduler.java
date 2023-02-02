package com.foodies.dirtydining.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(Data.class);

    @Autowired
    private Data data;

    @Scheduled(cron = "0 0 1 * * ?")
    public void dailyPopulateDbJob() {
        logger.info("Daily cron job executing.");
		data.updateDB();
        logger.info("Daily cron job finished.");
    }
}
