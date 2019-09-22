#!/usr/bin/env bash
cd running-Information-analysis-service
mvn clean install
cd target
java -jar running-Information-analysis-service-1.0-SNAPSHOT.jar