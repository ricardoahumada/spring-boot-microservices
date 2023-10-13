# DEPLOYMENT WITH KUBERNETES

## 1. Kubernetes commands

### build projects
`./jar-installer.sh`


### delete images
`docker rmi $(docker images | grep 'ricardoahumada') -f`

`docker images`


### build images
`docker build -t ricardoahumada/config-server:sb3 config-server`

`docker build -t ricardoahumada/products-service:sb3 products-service`

`docker build -t ricardoahumada/orders-service:sb3 orders-service`

`docker build -t ricardoahumada/gateway-server:sb3 gateway-server`

`docker images`

### push images
`docker push ricardoahumada/config-server:sb3`

`docker push ricardoahumada/products-service:sb3`

`docker push ricardoahumada/orders-service:sb3`

`docker push ricardoahumada/gateway-server:sb3`


### apply manifests
`kubectl apply -f config-server.rs.yaml && kubectl apply -f config-server.svc.yaml`

`kubectl apply -f products-service.rs.yaml && kubectl apply -f products-service.svc.yaml`

`kubectl apply -f orders-service.rs.yaml && kubectl apply -f orders-service.svc.yaml`

`kubectl apply -f gateway-server.rs.yaml && kubectl apply -f gateway-server.svc.yaml`

`kubectl get all`


### show services logs
`kubectl logs service/config-server -f`

`kubectl logs service/products-service -f`

`kubectl logs service/orders-service -f`

`kubectl logs service/gateway-server -f`


### port-forward services
`kubectl port-forward service/config-server 8888:8888`

`kubectl port-forward service/orders-service 9100:9100`

`kubectl port-forward service/products-service 9090:9090`

`kubectl port-forward service/gateway-server 8765:8765`


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


### delete individually
`kubectl delete service/config-server deployment.apps/config-server-deployment`

`kubectl delete service/products-service deployment.apps/products-service-deployment`

`kubectl delete service/orders-service deployment.apps/orders-service-deployment`

`kubectl delete service/gateway-server deployment.apps/gateway-server-deployment`


## 2.Using kustomization.yaml
#### Create
`kubectl apply -k ./`

`kubectl port-forward service/gateway-server 8765:8765`

#### Delete/clean up
`kubectl delete -k ./`