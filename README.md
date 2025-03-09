Pasos para uso de Docker Compose:

Una vez elaborado el archivo de docker-compose.yml debemo volver a empaquetar el proyecto con: mvn clean package -DskipTests Nota: Debemos saltarnos las pruebas por que la configuración va a fallar.
Construimos con un comando de Docker Compose indicando únicamente nuestro servicio: docker-compose build java_app
Consultamos que exista nuestra imagen con: docker images Nota: El nombre de la imagen está definida en el docker-compose.yml -> uce-java-app
Descargamos todas las imágenes que tengamos en el docker-compose.yml y levanta los contenedores con el orden especificado con: docker-compose up Nota: Si queremos que el log esté en segundo plano: docker-compose up -d Si queremos eliminar todos los contenedores levantados: docker-compose down Si queremos revisar que contenedores tenemos, podemos usar: docker ps Si queremos ver que imágenes tenemos descargadas hacemos: docker images Para eliminar las imágenes usamos el comando: docker image rm IMAGE_ID
