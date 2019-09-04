#!/bin/bash
oc new-project calcoolater
oc new-build --name calcoolater openshift/wildfly --binary=true
./scripts/deploy.sh
sleep 150
oc new-app --name calcoolater --image-stream=calcoolater
oc expose svc calcoolater
