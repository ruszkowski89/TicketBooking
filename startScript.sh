#!/bin/bash
docker pull redis
docker run -d --name redis -p 6379:6379 redis
./gradlew bootRun
