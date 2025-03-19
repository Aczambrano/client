
# Microservicio de Cliente

Este proyecto es un microservicio que maneja la gestión de clientes, proporcionando métodos básicos de CRUD (Crear, Leer y Actualizar) para interactuar con la base de datos MySQL. 

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot
- **MySQL** como base de datos
- **Docker** para la creación del contenedor

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes componentes:

- **Docker**: [Instalar Docker](https://docs.docker.com/get-docker/)

## Cómo Ejecutar el Proyecto

### 1. Construir la imagen Docker

Primero, debes construir la imagen Docker para el microservicio de cliente. En la raíz del proyecto, ejecuta el siguiente comando:

```bash
docker build -t app .
```

Este comando creará una imagen Docker llamada `cliente-microservice` a partir del `Dockerfile` proporcionado.

### 2. Ejecutar el contenedor

Una vez que la imagen esté construida, puedes ejecutar el contenedor usando:

```bash
docker run -d -p 8080:8080 --name app app
```

Este comando iniciará el contenedor en segundo plano (`-d`), mapeando el puerto 8080 del contenedor al puerto 8080 de tu máquina local. Esto permitirá que el microservicio sea accesible en `http://localhost:8080`.

### 3. Acceder a la API

El microservicio estará disponible en los siguientes endpoints:

- **POST /api/clients**: Crear un nuevo cliente.
- **GET /api/clients**: Obtener todos los clientes.
- **GET /api/clients/{id}**: Obtener un cliente por ID.
- **PUT /api/clients/{id}**: Actualizar un cliente existente por ID.

### 4. Detener el contenedor

Si deseas detener el contenedor, puedes hacerlo con el siguiente comando:

```bash
docker stop app
```

Para eliminar el contenedor detenido, usa:

```bash
docker rm app
```

## Notas Adicionales

- La base de datos MySQL ya está configurada dentro del contenedor Docker y no necesita configuración adicional.
- El microservicio espera que la base de datos MySQL esté disponible en el contenedor con las credenciales predeterminadas.
