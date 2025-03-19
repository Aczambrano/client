# Cliente Microservicio

Este proyecto es una aplicación cliente desarrollada con **Java**, utilizando el framework **Spring Boot** y una base de datos **MySQL**. Se ejecuta dentro de contenedores **Docker** para facilitar la configuración y despliegue.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- MySQL 8.0
- Docker & Docker Compose

## Requisitos previos

Antes de ejecutar la aplicación, asegúrese de tener instalados los siguientes programas:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Configuración y ejecución

1. **Clonar el repositorio**
   ```sh
   git clone <URL_DEL_REPOSITORIO>
   cd client
   ```

2. **Ir al directorio del proyecto**
   ```sh
   cd client
   ```

3. **Construir y levantar los contenedores**
   ```sh
   docker-compose up -d
   ```
   Esto iniciará:
   - Un contenedor para la base de datos MySQL (`mysql_container`)
   - Un contenedor para la aplicación cliente (`my_microservice`)

4. **Verificar que los contenedores estén corriendo**
   ```sh
   docker ps
   ```
   Debe mostrar algo similar a esto:
   ```
   CONTAINER ID   IMAGE          COMMAND                  STATUS            PORTS                               NAMES
   a1eda000641d   client-app     "java -jar app.jar"      Up a few seconds  0.0.0.0:8080->8080/tcp              my_microservice
   ad0f4631795b   mysql:latest   "docker-entrypoint.s…"   Up a few seconds  0.0.0.0:3306->3306/tcp, 33060/tcp   mysql_container
   ```

5. **Acceder a la aplicación**
   - La API estará disponible en: [http://localhost:8080](http://localhost:8080)

6. **Importar la colección de Postman**
   - Se encuentra en la raíz del proyecto con el nombre `Client Bayteq.postman_collection.json`.
   - Puede ser importada en [Postman](https://www.postman.com/) para realizar pruebas de la API.

7. **Apagar los contenedores**
   ```sh
   docker-compose down
   ```

## Notas adicionales

- Si la aplicación no inicia correctamente, revisar los logs:
  ```sh
  docker logs my_microservice
  ```
- Si hay cambios en el código fuente, reconstruir los contenedores:
  ```sh
  docker-compose down
  docker-compose up --build -d
  ```

---
**Autor:** Anderson

