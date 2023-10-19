# Install jenkins linux

## install jdk if needed
```
sudo apt update

# sudo apt install openjdk-17-jre
# java -version
```
## install pacakge
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

# Jenkins plugin
https://plugins.jenkins.io/kubernetes-cli/
https://geekdudes.wordpress.com/2020/01/03/minikube-configure-jenkins-kubernetes-plugin/

## Allow jenkins access to certification files

export KUBECONFIG=~/.kube/config
sudo apt install -y acl
setfacl -R -m u:jenkins:rwx /home/ubuntu/.minikube/profiles/minikube/


- Get from .kube/config the "server" and "client-certificate" values
- Add new credential to jenkins
	+ kind: secret text
	+ value: token string (output of `kubectl describe secrets/jenkins-token-rk2mg`)
- Jenkins – manage Jenkins – Configure system scroll to bottom
	+ Add a new cloud, select Kubernetes
	+ Kubernetes URL: value server from config file
	+ Kubernetes server certificate key: value certificate-authority from config file
	+ Credentials: credentials created in previous step.
	+ Click on “Test Connection” tab and you should get Connection test successful
 

## Example pipeline
pipeline {
    agent any

    stages {
         stage('Checkout') {
            steps {
                git branch: '[branch]',url: 'https://github.com/ricardoahumada/[repo]'
            }
        }  
        stage('Exexuting kubectl') {
            steps {
                echo 'Exexuting kubectl'
                withKubeConfig([credentialsId: 'k8-credentials']) {
                    sh 'kubectl apply -f deployment.yaml'
                    sh 'kubectl apply -f service.yaml'
                }
            }
        }
    }
}

