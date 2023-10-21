#!/bin/bash

# maven
wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz
sudo tar xf apache-maven-3.9.5-bin.tar.gz -C /opt
sudo ln -s /opt/apache-maven-3.9.5 /opt/maven
sudo ln -s /opt/maven/bin/mvn /usr/bin/mvn
mvn --version

# jenkins
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null

echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null

sudo apt-get update

sudo apt-get install jenkins -y

sudo service jenkins start
sudo service jenkins status


## Allow jenkins access to certification files
export KUBECONFIG=~/.kube/config
sudo apt install -y acl
setfacl -R -m u:jenkins:rwx /home/springboot/.minikube/profiles/minikube/

# Allow jenkins docker connect
sudo chmod 666 /var/run/docker.sock

