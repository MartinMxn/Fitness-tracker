package com.fitnesstracker.runninglocationsimulator.service.impl;


import com.fitnesstracker.runninglocationsimulator.model.GpsSimulatorRequest;
import com.fitnesstracker.runninglocationsimulator.model.Leg;
import com.fitnesstracker.runninglocationsimulator.model.Point;
import com.fitnesstracker.runninglocationsimulator.service.GpsSimulatorFactory;
import com.fitnesstracker.runninglocationsimulator.service.PathService;
import com.fitnesstracker.runninglocationsimulator.service.PositionService;
import com.fitnesstracker.runninglocationsimulator.support.NavUtils;
import com.fitnesstracker.runninglocationsimulator.task.LocationSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DefaultGpsSimulatorFactory implements GpsSimulatorFactory {

    @Autowired
    private PathService pathService;

    @Autowired
    private PositionService positionService;

    private final AtomicLong instanceCounter = new AtomicLong();

    @Override
    public LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest) {

        final LocationSimulator locationSimulator = new LocationSimulator(gpsSimulatorRequest);

        locationSimulator.setPositionInfoService(positionService);
        locationSimulator.setId(this.instanceCounter.incrementAndGet());

        final List<Point> points = NavUtils.decodePolyline(gpsSimulatorRequest.getPolyline());
        locationSimulator.setStartPoint(points.iterator().next());

        return prepareGpsSimulator(locationSimulator, points);
    }

    @Override
    public LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points) {
        locationSimulator.setCurrentPosition(null);

        final List<Leg> legs = createLegsList(points);
        locationSimulator.setLegs(legs);
        locationSimulator.setStartPosition();
        return locationSimulator;
    }

    private List<Leg> createLegsList(List<Point> points) {
        final List<Leg> legs = new ArrayList<Leg>();
        for (int i = 0; i < (points.size() - 1); i++) {
            Leg leg = new Leg();
            leg.setId(i);
            leg.setStartPosition(points.get(i));
            leg.setEndPosition(points.get(i + 1));
            Double length = NavUtils.getDistance(points.get(i), points.get(i + 1));
            leg.setLength(length);
            Double heading = NavUtils.getBearing(points.get(i), points.get(i + 1));
            leg.setHeading(heading);
            legs.add(leg);
        }
        return legs;
    }


}
