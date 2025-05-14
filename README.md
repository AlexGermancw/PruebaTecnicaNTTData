# Prueba Técnica Arquitectura Microservicio
# Proyecto Microservicios - Account Service & Customer Service

Este repositorio contiene dos microservicios:

- **`account-service`**: Gestiona cuentas y transacciones.
- **`customer-service`**: Gestiona información de clientes.

## Requisitos

- Java 17+
- Maven 3.8+
- Docker y Docker Compose

---

## Instrucciones de ejecución

### 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd <nombre-del-repositorio>
```
### 2. Abrir los servicios en entornos separados
Abre dos ventanas de terminal (o dos sesiones en tu IDE) y navega a los directorios:

account-service

customer-service

### 3. Construcción local
Ejecuta lo siguiente en ambos servicios para compilar el proyecto y descargar dependencias:
```bash
mvn install
```
### 4. Ejecución local
Para correr los microservicios localmente sin contenedores, ejecuta en cada servicio:
```bash
mvn spring-boot:run
```
### 5. Ejecución en contenedores
Para levantar la solución completa con base de datos y RabbitMQ en contenedores:
```bash
./deploy.sh
```
Este script:

Limpia builds anteriores.

Empaqueta los servicios.

Construye las imágenes Docker.

Ejecuta docker-compose up -d.

### 6. Acceso local a servicios y pruebas
- Account Service (Swagger UI)
http://localhost:8080/swagger-ui/index.html

- Customer Service (Swagger UI)
http://localhost:8081/swagger-ui/index.html

- Panel de administración RabbitMQ
http://localhost:15672
Usuario: guest
Contraseña: guest

### 7. Swagger publicado en la nube
- Customer Service desplegado en Azure
http://57.151.70.239:8083/swagger-ui/index.html

### 8. API publicada en Azure APIM (con autenticación por suscripción)
- Endpoint para listar clientes:
https://ntt-apim.azure-api.net/api/customers?subscription-key=0cdc6ed93c554095830e85b091b8e7e0

- ⚠️ Se incluye el archivo NTT_DATA_APIM.postman_collection.json con la colección de endpoints para pruebas desde Postman.

### 9. Pruebas con Postman
Importa el archivo NTT_DATA_APIM.postman_collection.json en Postman para acceder fácilmente a todos los endpoints y realizar pruebas funcionales de la solución.

## Notas
Asegúrate de que los puertos 8080, 8081, 5440 y 15672 estén disponibles en tu entorno local.
Puedes ajustar configuraciones de red, puertos y base de datos desde el archivo docker-compose.yml.
