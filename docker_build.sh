#!/bin/bash

sbt stage
docker build -t shk/rsvp-api .
docker tag shk/rsvp-api registry.srv.greenhat.consulting/shk/rsvp-api
docker push registry.srv.greenhat.consulting/shk/rsvp-api
