# DEPLOYMENT WITH DOCKER COMPOSE


### apply build images
`docker compose -f docker-compose-config-services.yaml build`

### apply manifests
`docker compose -f docker-compose-config-services.yaml up`

`docker compose -f docker-compose-config-services.yaml up -d`


### show running
`docker compose ps`
`docker compose -f docker-compose-config-services.yaml ps`


### show logs
`docker compose -f docker-compose-config-services.yaml logs --since 0 | more`

`docker compose naming-server --since 0 | more`

`docker compose config-server --since 0 | more`

`docker compose orders-service --since 0 | more`

`docker compose products-service --since 0 | more`

`docker compose consumer-service --since 0 | more`



### consume services
#### services
`curl localhost:9090/products  | json_pp`

`curl localhost:9100/orders  | json_pp`

`curl localhost:9100/actuator/env  | json_pp | more`

`curl -X POST -H "Content-Type: application/json"  localhost:9100/actuator/refresh`

`curl -H "Content-Type: application/json"  -d '{"product":1,"description":"New order","quantity":1}' localhost:9100/orders  | json_pp`


#### gateway
`curl localhost:8765/products  | json_pp`

`curl localhost:8765/orders  | json_pp`

`curl -H "Content-Type: application/json"  -d '{"product":1,"description":"New order","quantity":1}' localhost:8765/orders  | json_pp`


### stop/start
`docker compose -f docker-compose-config-services.yaml stop`
`docker compose -f docker-compose-config-services.yaml start`


### delete all (must be stopped)
`docker compose -f docker-compose-config-services.yaml rm`
