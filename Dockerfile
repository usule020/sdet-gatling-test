# Use an official Gatling image as the base image
FROM denvazh/gatling:latest

# Copy your Gatling test files into the container
COPY ./user-files /opt/gatling/user-files

