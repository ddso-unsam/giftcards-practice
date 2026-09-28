# giftcards-practice

Proyecto de práctica: plataforma de **giftcards (tarjetas de regalo)**. Una empresa crea
giftcards, una persona las recibe y las usa a través de una app, y el sistema notifica ese uso
a la empresa mediante un webhook. El sistema no maneja dinero ni el intercambio físico en el
comercio donde se presenta la giftcard.

Se implementa como una **API REST** (Spring Boot) con **persistencia en base relacional** (H2,
por ahora). Para el detalle de actores, alcance y los diagramas C4, ver
[`ARCHITECTURE.md`](ARCHITECTURE.md). Para las convenciones de código, ver
[`AGENTS.md`](AGENTS.md).

## Requisitos previos

Antes de comenzar la práctica, asegurate de tener instalado y configurado el siguiente software:

- **Git** (última versión estable recomendada).
- **JDK 21 (Temurin 21 LTS)**. El proyecto está desarrollado utilizando esta versión de Java Development Kit.
- **Apache Maven 3.9 o superior**, configurado para utilizar el JDK 21 (o usar el wrapper `./mvnw`, que no lo requiere instalado).
- Un entorno de desarrollo (IDE), como por ejemplo: IntelliJ IDEA, Visual Studio Code, etc.

Luego de clonar el repositorio, verificá que tu IDE esté configurado con:

- **JDK:** Temurin 21 (Java 21 LTS)
- **SDK del proyecto:** Java 21
- **Maven:** versión 3.9 o superior

## Estructura del proyecto

```
src/main/java/ar/edu/unsam/ddso/giftcards/
├── GiftcardsApplication.java   # entry point
├── controller/                 # @RestController — HTTP -> service
├── dto/                         # records de request/response
├── exception/                   # excepciones de infraestructura/HTTP + GlobalExceptionHandler
├── model/                       # entidades @Entity (reglas de negocio)
│   ├── enums/                   # enums del dominio
│   └── exceptions/               # excepciones de reglas de negocio
├── repository/                  # interfaces de acceso a datos (+ impl JPA / en memoria)
└── service/                     # orquestación (@Service)

src/main/resources/
├── application.properties        # nombre de la app + perfil activo
├── application-local.properties  # datasource H2 + JPA para desarrollo local
└── application-prod.properties   # datasource H2 + JPA para "prod" (misma H2 por ahora)

src/test/java/ar/edu/unsam/ddso/giftcards/
└── GiftcardsApplicationTests.java  # smoke test (contextLoads)
```

## Cómo correr los tests

```bash
./mvnw test
```

## Cómo ejecutar la app en local

```bash
./mvnw spring-boot:run
```

Por defecto corre con el perfil `local` (H2 in-memory, base `giftcards`, consola H2 habilitada
en `http://localhost:8080/h2-console`, JDBC URL `jdbc:h2:mem:giftcards`). Como todavía no hay
dominio implementado, la app levanta pero no expone endpoints propios.
