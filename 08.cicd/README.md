# Install jenkins linux

## install jdk if needed
```
sudo apt update

# sudo apt install openjdk-17-jre
# java -version
```
## install package
```
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null

echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null

sudo apt-get update

sudo apt-get install jenkins
```

## start service
```
sudo service jenkins status
sudo service jenkins start/stop
```

# Jenkins-docker

## Jenkins plugin
- https://plugins.jenkins.io/docker-workflow/
- https://www.jenkins.io/doc/pipeline/steps/docker-workflow/
 
## Config
- Add new credential to jenkins
	+ kind: username-pass
- use withDockerRegistry
- Example:
```
/** Docker hub case **/                
/*withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "" ]) {
    sh 'docker push ricardoahumada/python[repo]:[tag]'
}*/

/** ACR case **/
// To get acr url use: az acr list -o table
withDockerRegistry([ credentialsId: "acr-credentials", url: "https://ricardobootcampregistry.azurecr.io" ]) {
    sh 'docker push ricardobootcampregistry.azurecr.io/python[repo]:[tag]'
}
```

# Jenkins-kubernetes

## Jenkins plugin
- https://plugins.jenkins.io/kubernetes-cli/
- https://geekdudes.wordpress.com/2020/01/03/minikube-configure-jenkins-kubernetes-plugin/

## Allow jenkins access to certification files
```
export KUBECONFIG=~/.kube/config
sudo apt install -y acl
setfacl -R -m u:jenkins:rwx /home/springboot/.minikube/profiles/minikube/
```

## Config
- get minikube ip:
	+ `kubectl config vie`
- Method 1:
	+ Create a secret file credentials in jenkins
- Mehthod 2:
	+ Get from .kube/config the "server" and "client-certificate" values
	+ Add new credential to jenkins
		* kind: secret text
		* value: token string (output of `kubectl describe secrets/jenkins-token-rk2mg`)
	+ Jenkins – manage Jenkins – Configure system scroll to bottom
		* Add a new cloud, select Kubernetes
		* Kubernetes URL: value server from config file
		* Kubernetes server certificate key: value certificate-authority from config file
		* Credentials: credentials created in previous step.
		* Click on “Test Connection” tab and you should get Connection test successful
- use withDockerRegistry.
- Example:
```
/**
    Uses plugin: https://plugins.jenkins.io/kubernetes-cli/
    Need to create credentials named "k8-credentials"  uploading minikube config file.
    Use: "scp ubuntu@[IP]:.kube/config k8-config" to download config file with name k8-config
**/

 /** Minikube case **/                
withKubeConfig([credentialsId: 'k8-credentials',clusterName: 'minikube']) {
    sh 'kubectl apply -f [path_to]/deployment.yaml'
    sh 'kubectl apply -f [path_to]/service.yaml'
    sh 'kubectl port-forward service/service_name 5000:5000 &'
}

/** AKS case **/                
// For getting the server url use: "az aks list -o table" and use "Fqdn" value
/*withKubeConfig([credentialsId: 'k8-credentials',serverUrl:'https://saving-kitten-k8s-5846b313.hcp.eastasia.azmk8s.io'],clusterName: '[CONTEXT-NAME]') {
    sh 'kubectl apply -f [path_to]/deployment.yaml'
    sh 'kubectl apply -f [path_to]/service.yaml'
    sh 'kubectl port-forward service/service_name 80:5000 &'
    sleep 5 // give 5 secs to stablish the port forward
}*/

```