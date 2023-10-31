# RSO: Image metadata microservice

## Prerequisites

```bash
docker run -d --name pg-image-metadata -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=image-metadata -p 5432:5432 postgres:13
```

## Build and run commands
```bash
mvn clean package
cd api/target
java -jar work-clock-api-1.0.0-SNAPSHOT.jar
```
Available at: localhost:8080/v1/workevent

## Docker commands
```bash
docker build -t workclock .   
docker images
docker run workclock    
docker tag workclock janezs12/workclock   
docker push janezs12/workclock
```

## Kubernetes
```bash
kubectl version
kubectl --help
kubectl get nodes
kubectl create -f .\deployment.yaml 
kubectl apply -f .\deployment.yaml 
kubectl get services 
kubectl get deployments
kubectl get pods
kubectl logs work-clock-deployment-84db9697f5-fk9sp
kubectl delete pod work-clock-deployment-84db9697f5-fk9sp
```

## Scale
```bash
kubectl scale deployment/work-clock-deployment-v2 --replicas=0 --namespace=default
```