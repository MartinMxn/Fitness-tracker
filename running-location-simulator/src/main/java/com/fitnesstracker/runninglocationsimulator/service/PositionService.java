package com.fitnesstracker.runninglocationsimulator.service;

import com.fitnesstracker.runninglocationsimulator.model.CurrentPosition;

public interface PositionService {

    void processPositionInfo(long id,
                             CurrentPosition currentPosition,
                             boolean sendPositionsToDistributionService);

}