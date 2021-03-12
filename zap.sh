#!/usr/bin/env bash

set -x

startZap() {
docker pull owasp/zap2docker-stable

docker run -d --rm \
    --name zap \
    -p 8081:8081 \
    -i owasp/zap2docker-stable zap.sh -daemon \
    -port 8081 \
    -host 0.0.0.0 \
    -config api.disablekey=true \
    -config api.addrs.addr.name=.* \
    -config api.addrs.addr.regex=true
}

getContainerIP(){
ip="$(docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' zap)"
echo "${ip}"
}

runGaugeTests(){
PROXY_IP=${1}
docker run --rm -it \
    -v "$(pwd)":/usr/src/app \
    -v "$HOME/.m2:/root/.m2" \
    -e ZAP_PROXY=true \
    -e ZAP_PROXY_IP="${PROXY_IP}" \
    sitture/docker-gauge-java:latest mvn clean test
}

startZap
runGaugeTests "$(getContainerIP)"