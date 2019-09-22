package com.fitnesstracker.runninglocationsimulator.rest;

import com.fitnesstracker.runninglocationsimulator.model.*;
import com.fitnesstracker.runninglocationsimulator.service.GpsSimulatorFactory;
import com.fitnesstracker.runninglocationsimulator.service.PathService;
import com.fitnesstracker.runninglocationsimulator.support.MedicalInfoUtils;
import com.fitnesstracker.runninglocationsimulator.task.LocationSimulator;
import com.fitnesstracker.runninglocationsimulator.task.LocationSimulatorInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api")
public class LocationSimulatorRestApi {

    @Autowired
    private PathService pathService;

    @Autowired
    private GpsSimulatorFactory gpsSimulatorFactory;

    @Autowired
    private AsyncTaskExecutor taskExecutor;

    private Map<Long, LocationSimulatorInstance> taskFutures = new HashMap<>();

    @RequestMapping("/simulation")
    public List<LocationSimulatorInstance> simulation() {
        final SimulatorInitLocations fixture = this.pathService.loadSimulatorFixture();

        final List<LocationSimulatorInstance> instances = new ArrayList<>();
        final List<Point> lookAtPoints = new ArrayList<>();

        final Set<Long> instanceIds = new HashSet<>(taskFutures.keySet());

        for (GpsSimulatorRequest gpsSimulatorRequest : fixture.getGpsSimulatorRequests()) {

            final LocationSimulator locationSimulator = gpsSimulatorFactory.prepareGpsSimulator(gpsSimulatorRequest);
            lookAtPoints.add(locationSimulator.getStartPoint());
            instanceIds.add(locationSimulator.getId());

            final Future<?> future = taskExecutor.submit(locationSimulator);
            final LocationSimulatorInstance instance = new LocationSimulatorInstance(locationSimulator
                    .getId(), locationSimulator, future);
            taskFutures.put(locationSimulator.getId(), instance);
            instances.add(instance);
        }

        return instances;
    }

    @RequestMapping("/status")
    public Collection<LocationSimulatorInstance> status() {
        return taskFutures.values();
    }

    @RequestMapping("/cancel")
    public int cancel() {
        int numberOfCancelledTasks = 0;
        for (Map.Entry<Long, LocationSimulatorInstance> entry : taskFutures.entrySet()) {
            LocationSimulatorInstance instance = entry.getValue();
            instance.getLocationSimulator().cancel();
            boolean wasCancelled = instance.getLocationSimulatorTask().cancel(true);
            if (wasCancelled) {
                numberOfCancelledTasks++;
            }
        }
        taskFutures.clear();
        return numberOfCancelledTasks;
    }

    @RequestMapping("/init-locations")
    public SimulatorInitLocations startLocations() {

        final List<DirectionInput> directions = this.pathService.loadDirectionInput();
        final SimulatorInitLocations fixture = new SimulatorInitLocations();

        for (DirectionInput directionInput : directions) {

            final GpsSimulatorRequest gpsSimulatorRequest = new GpsSimulatorRequest();
            gpsSimulatorRequest.setExportPositionsToMessaging(true);
            gpsSimulatorRequest.setMove(true);

            String polyline = this.pathService.getCoordinatesFromGoogleAsPolyline(directionInput);
            gpsSimulatorRequest.setPolyline(polyline);
            gpsSimulatorRequest.setReportInterval(1000);
            gpsSimulatorRequest.setSpeed(50.0);
            gpsSimulatorRequest.setExportPositionsToMessaging(true);
            gpsSimulatorRequest.setSecondsToError(60);
            gpsSimulatorRequest.setRunnerStatus(RunnerStatus.NONE);
            //@Nike Running
            gpsSimulatorRequest.setMedicalInfo(MedicalInfoUtils.getRandomMedicalInfo());
            fixture.getGpsSimulatorRequests().add(gpsSimulatorRequest);
        }

        return fixture;
    }
}

