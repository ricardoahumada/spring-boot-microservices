# CLOUD DEPLOYMENT USING ECS
- Create a cluster
- Create a task
- Create a service
	+ Without load balancer
	+ With load balancer
	+ Geenerate a SG
	+ Internet access
- Show logs
- Use aws console for curl
	+ curl http://18.116.165.60:9090/products | jq
- Delete cluster
	+ First update service to scale to o
	+ delete service
	+ delete task


# CLOUD DEPLOYMENT USING ECS - COPILOT

## 1. Install tools

### AWS CLI
`sudo apt-get update`

`sudo apt-get install awscli`

`aws --version`

`aws configure`


#### delete config (if needed)
`rm -v ~/.aws/config ~/.aws/credentials`

### install copilot
`curl -Lo copilot https://github.com/aws/copilot-cli/releases/latest/download/copilot-linux && chmod +x copilot && sudo mv copilot /usr/local/bin/copilot`

`copilot --help`

### clone git repo with services
`git clone <repo-url>`

### deploy a service
`cd <path_to_service_folder>`

`copilot init` 
#### then answer the questions

`copilot init --app "<app_name>" --name "<app_name>" --type 'Load Balanced Web Service'   --dockerfile './Dockerfile' --port <port> --deploy`

#### Example:
`copilot init --app "spring-boot-ecosystem" --name "products-service" --type 'Load Balanced Web Service' --dockerfile './Dockerfile' --port 9090 --deploy`

### delete services
`copilot app delete`
