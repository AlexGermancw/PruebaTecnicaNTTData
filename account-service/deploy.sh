#!/bin/bash

set -e 

echo " Limpiando builds anteriores..."
mvn clean

echo " Empaquetando servicios (sin tests)..."
mvn package -DskipTests

echo " Construyendo imágenes Docker..."
docker-compose build

echo " Levantando servicios..."
docker-compose up -d

echo " Despliegue completo. Verifica en http://localhost:8080"
