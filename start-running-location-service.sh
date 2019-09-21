#!/usr/bin/env bash
cd supply-locaton-service
mvn clean install
cd target
java -jar supply-locaton-service-1.0.0-SNAPSHOT.jar