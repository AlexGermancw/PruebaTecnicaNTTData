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

### 6. Acceso y pruebas
API Account Service: http://localhost:8080/swagger-ui.html o /swagger-ui/index.html

API Customer Service: http://localhost:8081/swagger-ui.html o /swagger-ui/index.html

RabbitMQ Admin: http://localhost:15672 (usuario: guest, clave: guest)

### Notas
Asegúrate de que los puertos 8080, 8081, 5440 y 15672 estén disponibles.

Puedes modificar el archivo docker-compose.yml para cambiar configuraciones de red, puertos y bases de datos si es necesario.

## Pruebas con Postman
Se adjunta el archivo .json de colección de Postman con los endpoints necesarios para probar la solución. Puedes importarlo en Postman para empezar a hacer peticiones.
