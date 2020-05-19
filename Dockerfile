# Download imagem
# docker pull dosel/zalenium
# docker run --rm -ti --name zalenium -d -p 4444:4444 -e PULL_SELENIUM_IMAGE=true -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start
#Contruir imagem
#    docker build -t nome-projeto -f ./Dockerfile .
#Rodar os testes
#    docker run --network="host" conectcar mvn clean test -Denv=qa
FROM maven:3.5.3-jdk-8-alpine
# copy the project files
COPY ./pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src