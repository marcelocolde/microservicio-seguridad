#!/bin/bash
SPRING_PROFILE_ACTIVE=dev

cp target/ms-products-*.jar ms-products.jar

java -jar ms-products.jar --spring.profiles.active=$SPRING_PROFILE_ACTIVE
