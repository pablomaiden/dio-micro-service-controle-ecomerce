FROM gradle:8.11.1-jdk-21-and-23

RUN apt-get update && apt-get install -qq -y --no-install-recommends

ENV INSTALL_PATH /warehouse

# Adicione esta linha para limpar o cache do Gradle
#RUN rm -rf /home/gradle/.gradle/caches/

RUN mkdir $INSTALL_PATH

WORKDIR $INSTALL_PATH

COPY . .

#RUN gradle clean build