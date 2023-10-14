#!/bin/bash

echo "Pushing config-server image..."
docker push ricardoahumada/config-server:sb3.1

echo "Pushing gateway-server image..."
docker push ricardoahumada/gateway-server:sb3.1

echo "Pushing products-service image..."
docker push ricardoahumada/products-service:sb3.1

echo "Pushing orders-service image..."
docker push ricardoahumada/orders-service:sb3.1

echo "Pushing users-management-service image..."
docker push ricardoahumada/users-management-service:sb3.1
