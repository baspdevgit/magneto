# Magneto

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men. Este proyecto permite detectar si un
humano es mutante bas谩ndose en su secuencia de ADN.

## Comenzando 馃殌

Dentro del proyecto se encuentra una carpeta llamada **project-resources**, la cual contiene el contrato y las pruebas de los servicios expuestos, junto con el reporte de pruebas sobre la versi贸n actual. Para prop贸sitos de validaci贸n en tal caso que no se despliegue el proyecto localmente. 


### Pre-requisitos 馃搵

**AWS**

Para el consumo sobre AWS se requiere abrir la colecci贸n de Postman y consumir seg煤n el contrato (Swagger). Ambos recursos encontrados en la carpeta descrita anteriormente **(project-resources)**

_Cabe resaltar que la base de datos de AWS se encontrar谩 vac铆a para no alterar las futuras pruebas_

**Local**

Para ejecutar localmente, puede sincronizar el repositorio git, la  gesti贸n de dependencias se hace mediante Gradle y Maven, debido a esto es necesario tener acceso al repositorio central de Maven y sincronizar gradle para realizar la descarga de todas las dependencias requeridas para la implementaci贸n. 

En el 谩mbito de base de datos, la soluci贸n ha sido implementada sobre MySQL, debido a esto es necesario tener instalado MySQL Server, en caso de usar otro gestor de base de datos, se debe configurar el archivo de propiedades **application-default.yml** con el nuevo datasource, de igual manera agregar el driver de acceso mediante R2DBC en **build-dependencies.gradle**.

Igual que el consumo sobre AWS, utilizar la colecci贸n Postman y cambiar a url local.

### Instalaci贸n 馃敡

- Crea la base de datos **magneto** y luego ejecuta el script **project-resources/scripts/table_script.sql**

```
CREATE TABLE `magneto`.`DNA` (
  `DNA_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `DNA_VALUE` MEDIUMTEXT NOT NULL,
  `DNA_MUTANT` TINYINT NOT NULL,
  PRIMARY KEY (`DNA_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
```

El cual creara la tabla solicitada para agregar los ADN analizados. 

- Sincronizar el repositorio Git.
- Sincronizar dependencias y luego limpiar y construir. Esto con el fin de obtener el proyecto listo para desplegar. _Tenga en cuenta que esto puede realizarlo desde consola o con un IDE que soporte Java_
- Una vez construido, se creara una carpeta llamada build, la cual generara la libreria **build/libs/Magneto-1.0.jar** listo para desplegar.

## Ejecutando las pruebas 鈿欙笍
Dentro del repositorio encontramos **project-resources/reports** con el reporte de las pruebas generadas sobre la versi贸n final. Igualmente, al momento de construir el proyecto, dentro de la carpeta **build** se generaran los reportes de la 煤ltima versi贸n compilada.

### Evidencia pruebas end-to-end 馃敥

A continuaci贸n se agregan algunas evidencias de la colecci贸n de Postman, de como deber铆a responder cuando son llamadas las API.

- Verificaci贸n de ADN
    
    
![img.png](readme-resources/postman_evidence_1.png)

![img_1.png](readme-resources/postman_evidence_2.png)

- Estad铆sticas

![img_2.png](readme-resources/postman_evidence_3.png)

## Construido con 馃洜锔?

Las herramientas utilizadas para la implementaci贸n de la soluci贸n son: 

* [AWS](https://aws.amazon.com/) - Despliegue en la nube
* [MySQL](https://www.mysql.com/) - Gestor de base de datos

* [Java 8](https://www.java.com/es/download/help/java8.html) - Lenguaje programaci贸n
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework de desarrollo
* [Gradle](https://gradle.org/) - Configuraci贸n del proyecto
* [Maven](https://maven.apache.org/) - Gestor de dependencias
* [Jacoco](https://www.eclemma.org/jacoco/) - An谩lisis de pruebas y cobertura. 

Otros
* [Bizagi](https://www.bizagi.com/) - Dise帽o proceso
* [Swagger](https://editor.swagger.io/) - Dise帽o contrato
* [Postman](https://www.postman.com/) - Consumo de APIs


## Autores 鉁掞笍


* **Bryan Silva** - *Dise帽o, desarrollo, implementaci贸n y documentaci贸n* - [bryansilvagit](https://github.com/bryansilvagit) - [basp.95@outlook.com](mailto:basp.95@outlook.com)

## Licencia 馃搫

Este proyecto est谩 desarrollado sobre la prueba t茅cnica solicitada por [Mercado Libre](https://github.com/bryansilvagit/magneto/blob/4120ac3ab80474bd609d470dca26fa184e7da86b/project-resources/Mutantes.pdf)



---
鈱笍 con 鉂わ笍 por [bryansilvagit](https://github.com/bryansilvagit) - [basp.95@outlook.com](mailto:basp.95@outlook.com)