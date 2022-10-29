# DEPLOYMENT WITH KUBERNETES AND CLOUD

## 1. Kubernetes commands

### build projects
`./jar-installer.sh`


### delete images
`docker rmi $(docker images | grep 'ricardoahumada') -f`

`docker images`


### build images
`docker build -t ricardoahumada/config-server:v1 config-server`

`docker build -t ricardoahumada/products-service:v1 products-service`

`docker build -t ricardoahumada/orders-service:v1 orders-service`

`docker build -t ricardoahumada/gateway-server:v1 gateway-server`

`docker images`

### push images
`docker push ricardoahumada/config-server:v1`

`docker push ricardoahumada/products-service:v1`

`docker push ricardoahumada/orders-service:v1`

`docker push ricardoahumada/gateway-server:v1`


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


### Using kustomization.yaml
#### Create
`kubectl apply -k ./`

`kubectl port-forward service/gateway-server 8765:8765`

#### Delete/clean up
`kubectl delete -k ./`


## 2. Provision EKS cluster

### AWS CLI
`sudo apt-get update`

`sudo apt-get install awscli`

`aws --version`

`aws configure`


#### delete config (if needed)
`rm -v ~/.aws/config ~/.aws/credentials`


### Install eksctl
`curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp`

`sudo mv /tmp/eksctl /usr/local/bin`

`eksctl version`


## Create cluster with eksctl
### ⚠️Fist Create a role and associate to EC2 with EKS permissions!

`eksctl create cluster --name <your-cluster-name> --version <version-number> --nodes=1 --node-type=t2.small`

#### example:
`eksctl create cluster --name spring-boot-cluster --nodes=2 --node-type=t2.small`

`aws eks list-clusters`

## Connect kubeclt to EKS cluster
`aws sts get-caller-identity`

`aws eks --region <region> update-kubeconfig --name <cluster_name>`

#### example:
`aws eks update-kubeconfig --name spring-boot-cluster`

### ⚠️Change v1alpha1 to v1beta1:
`nano .kube/config`


### show/change context and nodes
`kubectl config get-contexts`

`kubectl config current-context`

`kubectl config use-context CONTEXT_NAME`

`kubectl get nodes `


## Delete a cluster
`eksctl delete cluster spring-boot-cluster`
