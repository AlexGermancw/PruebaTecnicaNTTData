# Usa una imagen ligera de Java 17
FROM openjdk:17-jdk-alpine

# Crea un directorio para la app
WORKDIR /app

# Copia el JAR compilado al contenedor
COPY target/customer-service-0.0.1-SNAPSHOT.jar app.jar

# Expón el puerto que usa tu microservicio
EXPOSE 8083

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
