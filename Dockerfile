FROM bellsoft/liberica-openjdk-alpine:17.0.8

# install curl and jq: a tool to filter curl command output
RUN apk add curl jq

# setup work directory inside the container
WORKDIR /home/selenium-docker

# add the required files into the work director
ADD target/docker-resources     ./
ADD runner.sh   runner.sh

# run the script
ENTRYPOINT sh runner.sh