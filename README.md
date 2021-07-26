# connect-4-backend

## Goal

This repository is to create a variety of mediocre AIs that can play against each other.

Inspiration came from [this great video](https://www.youtube.com/watch?v=DpXy041BIlA) about mediocre chess AIs.

## User Interface

[This repo](https://github.com/davisharlan/connect-4) contains a React UI that interacts with this api.

## Development

To run server locally

```sbt run```

## Deploy to Kubernetes locally

1. Build the docker image

    ```docker build . -t connect-4/connect-4-backend```

1. Start minikube

    ```minikube start```

1. Create the deployment

    ```kubectl create -f kubernetes/deployment.yml```

1. Create the service 

    ```kubectl create -f kubernetes/service.yml```

1. Port forward one of the pods to your port of choice

    ```kubectl port-forward {POD} {PORT}:8081```
