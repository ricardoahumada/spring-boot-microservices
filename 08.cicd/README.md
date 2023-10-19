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
