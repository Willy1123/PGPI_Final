FROM amazoncorretto:11-alpine-jdk

# Creamos el directorio donde se alojará la aplicación
RUN mkdir -p /app

# Copiamos el jar de la aplicación y los json de configuración
COPY target/*.jar /app/app.jar
COPY target/classes/*.json /app/src/main/resources/

# Establecemos el directorio de trabajo
WORKDIR /app

# Ejecutamos la aplicación
CMD ["java", "-jar", "app.jar"]
