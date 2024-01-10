#!/bin/bash

path_core="../"
path_services="../../workclock-services"
path_socket="../../workclock-socket"
path_stats="../../workclock-stats"

image_core="janezs12/workclock-core"
image_services="janezs12/workclock-services"
image_socket="janezs12/workclock-socket-v4"
image_stats="janezs12/workclock-stats"


# Build Docker images
docker build -t "$image_core" "$path_core"
docker build -t "$image_services" "$path_services"
docker build -t "$image_socket" "$path_socket"
docker build -t "$image_stats" "$path_stats"

echo "Docker images built successfully: $image_core, $image_services, $image_socket"

cd "$path_core"
docker-compose -p workclock up





