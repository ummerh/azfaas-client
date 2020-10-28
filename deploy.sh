#!/bin/bash

login_docker(){
    az acr login --resource-group=HK-eforms-aks-infra --name=eacloudacr
}
build_app(){
   git pull
   mvn compile jib:build -DskipTests
}

login_docker
build_app