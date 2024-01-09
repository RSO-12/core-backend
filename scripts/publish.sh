#!/bin/bash

path_core="../"
path_services="../../workclock-services"
path_socket="../../workclock-socket"
path_stats="../../workclock-stats"

image_core="janezs12/workclock-core"
image_services="janezs12/workclock-services"
image_socket="janezs12/workclock-socket-v3"
image_stats="janezs12/workclock-stats"

# Push Docker images
docker push "$image_core"
docker push "$image_services"
docker push "$image_socket"
docker push "$image_stats"

echo "Docker images published successfully: $image_core, $image_services, $image_socket, $image_stats"