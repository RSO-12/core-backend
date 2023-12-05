#!/bin/bash

path_core="../"
path_services="../../workclock-services"
path_socket="../../workclock-socket"

image_core="janezs12/workclock-core"
image_services="janezs12/workclock-services"
image_socket="janezs12/workclock-socket"


# Build Docker images
docker build -t "$image_core" "$path_core"
docker build -t "$image_services" "$path_services"
docker build -t "$image_socket" "$path_socket"

echo "Docker images built successfully: $image_core, $image_services, $image_socket"

cd "$path_core"
docker-compose -p workclock up





