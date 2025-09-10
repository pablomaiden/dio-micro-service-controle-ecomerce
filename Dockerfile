FROM gradle:8.1.1-jdk21-focal

RUN apt-get update && apt-get install -qq -y --no-install-recommends

ENV INSTALL_PATH /storefront

RUN mkdir $INSTALL_PATH

WORKDIR $INSTALL_PATH

COPY . .