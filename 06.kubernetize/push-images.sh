#!/bin/bash

echo "Pushing config-server image..."
docker push ricardoahumada/config-server:v3

echo "Pushing gateway-server image..."
docker push ricardoahumada/gateway-server:v3

echo "Pushing products-service image..."
docker push ricardoahumada/products-service:v3

echo "Pushing orders-service image..."
docker push ricardoahumada/orders-service:v3

echo "Pushing users-management-service image..."
docker push ricardoahumada/users-management-service:v3
