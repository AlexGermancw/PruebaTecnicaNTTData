# Usa una imagen ligera de Java 17
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR compilado al contenedor
COPY target/account-service-0.0.1-SNAPSHOT.jar app.jar

# Expón el puerto utilizado por el microservicio
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
