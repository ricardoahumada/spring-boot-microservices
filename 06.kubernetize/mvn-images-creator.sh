#!/bin/bash

echo "Creating config-server image..."
cd ./config-server
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ricardoahumada/config-server:sb3.1
cd ..

echo "Creating gateway-server image..."
cd ./gateway-server
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ricardoahumada/gateway-server:sb3.1
cd ..

echo "Creating products-service image..."
cd ./products-service
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ricardoahumada/products-service:v3
cd ..

echo "Creating orders-service image..."
cd ./orders-service
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ricardoahumada/orders-service:v3
cd ..

echo "Creating users-management-service image..."
cd ./users-management-service
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ricardoahumada/users-management-service:sb3.1
cd ..

docker images | grep "sb3.1"