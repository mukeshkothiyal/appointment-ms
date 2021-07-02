# bring down all containers
docker-compose down

#### start : bring up essential services first : db, eureka, cloud-config ####

# database, service discovery
docker-compose  up -d db service-discovery

# brief pause before registering services
sleep 5s

#### end : bring up essential services first : db, eureka, cloud-config ####

# build app services
mvn clean install -f myappointment/pom.xml

# remove old app service images
docker rmi appointment-api:latest

# bring up all remaining services
docker-compose up -d appointment-api

# build api gateway
mvn clean install -f api-gateway/pom.xml

# remove old app service images
docker rmi api-gateway:latest

## bring up all remaining infra services
docker-compose up -d grafana prometheus api-gateway

# delete all dangling (mostly <non>:<none>) images
docker rmi $(docker images -f "dangling=true" -q)