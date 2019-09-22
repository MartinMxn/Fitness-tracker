package com.fitnesstracker.runninglocationsimulator.service;



import com.fitnesstracker.runninglocationsimulator.model.GpsSimulatorRequest;
import com.fitnesstracker.runninglocationsimulator.model.Point;
import com.fitnesstracker.runninglocationsimulator.task.LocationSimulator;

import java.util.List;

public interface GpsSimulatorFactory {

    LocationSimulator prepareGpsSimulator(GpsSimulatorRequest gpsSimulatorRequest);

    LocationSimulator prepareGpsSimulator(LocationSimulator locationSimulator, List<Point> points);

}
