#!/bin/bash

docker build -t expense-tracker-rest:v1 .

docker run -itd --name api -p 8080:8080 expense-tracker-rest:v1