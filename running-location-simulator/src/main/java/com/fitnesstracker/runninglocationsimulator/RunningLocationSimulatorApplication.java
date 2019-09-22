package com.fitnesstracker.runninglocationsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableScheduling
public class RunningLocationSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningLocationSimulatorApplication.class, args);
    }

}
