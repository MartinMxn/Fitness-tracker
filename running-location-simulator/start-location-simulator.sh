#!/usr/bin/env bash
cd running-location-simulator
mvn clean install
cd target
java -jar running-location-simulator-0.0.1-SNAPSHOT.jar