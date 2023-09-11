# Project Name

SDET Gatling


## Project Description

Runs Gatling in a docker container

### Prerequisites

Ensure that Docker is installed on your local machine

## Getting Started

1. Open a terminal, navigate to the directory containing your Dockerfile, and run the following command to build the Docker image:
```bash
docker build -t [DOCKER_CONTAINER_NAME] .
```
Replace [DOCKER_CONTAINER_NAME] with a suitable name for your Docker image

2. After the image is built, you can run Gatling tests inside a Docker container. You can use the following command to run the load simulation tests
```bash
docker run -it --rm [DOCKER_CONTAINER_NAME] -s apicenter.ApiSimulation
```

3. After running the test, Gatling will generate HTML reports in the container. To view the results, you can copy the report files out of to your host machine. To copy the report to your local machine, you can use the following command:
```bash
docker cp container_id:/opt/gatling/results /path/to/local/results
```
Replace container_id with the actual ID of the container running the Gatling test. Set the correct path of the report files to your local machine.

To find the container_id, you can use the following command, which lists all containers
```bash
docker ps -la
```