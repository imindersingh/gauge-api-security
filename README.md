# Gauge API Security Testing

Example repo using gauge framework and owasp for security testing APIs.

## Pre-reqs
- Java 11
- Maven
- Docker

## How it works
The tests are run using docker-gauge-java (https://github.com/sitture/docker-gauge-java) and proxied to zap running in docker.
Security tests would then be triggered using the proxied data and calling zap's apis.

## Usage
Run the following from the root of the project:

``./zap``

This will run zap in daemon mode and then run the tests through docker and proxy the http requests.

To verify, go to `http://localhost:8081/JSON/core/view/urls` and `http://localhost:8081/JSON/core/view/message/?id=1`, you should see the urls and request/responses which have been logged by zap 

## TODO
1. Build OWASP Zap library for handling proxied data
2. Implement tests and steps to run security scans against captured endpoints
3. Update script to trigger security tests 
4. Update to use docker compose
5. Build out jenkins pipeline
