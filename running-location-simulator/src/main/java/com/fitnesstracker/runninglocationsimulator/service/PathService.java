package com.fitnesstracker.runninglocationsimulator.service;


import com.fitnesstracker.runninglocationsimulator.model.DirectionInput;
import com.fitnesstracker.runninglocationsimulator.model.Point;
import com.fitnesstracker.runninglocationsimulator.model.SimulatorInitLocations;

import java.util.List;


public interface PathService {

    SimulatorInitLocations loadSimulatorFixture();

    List<DirectionInput> loadDirectionInput();

    List<Point> getCoordinatesFromGoogle(DirectionInput directionInput);

    String getCoordinatesFromGoogleAsPolyline(DirectionInput directionInput);

}