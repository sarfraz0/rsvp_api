FROM openjdk:8-alpine

RUN apk add --no-cache bash
COPY target/universal/stage /opt/rsvp
ENTRYPOINT /opt/rsvp/bin/shk-rsvp-api
