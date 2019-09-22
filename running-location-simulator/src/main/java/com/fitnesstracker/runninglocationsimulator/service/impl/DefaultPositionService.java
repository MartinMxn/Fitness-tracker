package com.fitnesstracker.runninglocationsimulator.service.impl;


import com.fitnesstracker.runninglocationsimulator.model.CurrentPosition;
import com.fitnesstracker.runninglocationsimulator.service.PositionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class DefaultPositionService implements PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPositionService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Value("${com.ross.running.location.distribution}")
//    private String runningLocationDistribution;

    public DefaultPositionService() {
        super();
    }

    @HystrixCommand(fallbackMethod = "processPositionInfoFallback")
    @Override
    public void processPositionInfo(long id, CurrentPosition currentPosition,
                                    boolean sendPositionsToDistributionService) {

        String runningLocationDistribution = "http://running-location-distribution";
        if (sendPositionsToDistributionService) {
            log.info("Simulator is callling distribution REST API");
            this.restTemplate.postForLocation(runningLocationDistribution + "/api/locations", currentPosition);

        }

    }

    public void processPositionInfoFallback(long id, CurrentPosition currentPosition,
                                            boolean sendPositionsToDistributionService) {
        LOGGER.error("Hystrix Fallback Method. Unable to send message for distribution.");
    }

}
