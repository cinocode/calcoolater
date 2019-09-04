#!/bin/bash
mvn clean package
mkdir -p target/oc_deploy/deployments
cp target/calcoolater*.war target/oc_deploy/deployments
oc start-build calcoolater --from-dir=target/oc_deploy/deployments
