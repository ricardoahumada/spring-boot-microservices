# CLOUD DEPLOYMENT USING EKS

## 1. Install tools

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

## 2. Provision EKS cluster

## Create cluster with eksctl
>⚠️Fist Create a role and associate to EC2 with EKS permissions!\
>⚠️Add role to instance: Actions > Security > Modify IAM role

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


## 3.Delete the cluster
`eksctl delete cluster <cluster_name>`

#### example:
`eksctl delete cluster spring-boot-cluster`
