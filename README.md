# Users CLI con DDD y arquitectura hexagonal

Ejemplo pedagógico simple para comprender las bases de Domain-Driven Design (DDD) y arquitectura hexagonal en Java, sin depender de frameworks avanzados ni contenedores de inyección de dependencias.

## Objetivo

El proyecto muestra cómo estructurar y organizar el código separando las reglas de negocio, los casos de uso, los puertos y los adaptadores.

La intención no es construir una aplicación lista para producción. El objetivo es facilitar el aprendizaje de los conceptos fundamentales antes de incorporar herramientas como Spring, bases de datos, proveedores de caché, ORM o mecanismos automáticos de inyección de dependencias.

## Casos de uso

La aplicación permite gestionar usuarios desde una interfaz de línea de comandos:

1. Registrar un usuario.
2. Buscar un usuario por ID.
3. Listar los usuarios registrados.

Los datos se almacenan en memoria durante la ejecución de la aplicación.

## Arquitectura

El proyecto aplica arquitectura hexagonal para mantener las reglas de negocio independientes de la interfaz de usuario y del mecanismo de persistencia.

```text
src/main/java/com/jcaa/udec/collections
├── domain
│   ├── core
│   │   ├── exception
│   │   ├── model
│   │   └── valueobject
│   └── port
│       └── out
├── application
│   └── service
├── adapter
│   └── persistence
│       └── memory
└── entrypoint
    ├── cli
    └── controller
```

### Dominio

Contiene el modelo `Usuario`, las excepciones y los Value Objects responsables de proteger las invariantes:

- `UsuarioId`
- `NombreUsuario`
- `Password`
- `Email`

### Aplicación

Contiene los servicios que coordinan los casos de uso y se comunican con el exterior mediante puertos.

### Adaptadores

Implementan persistencia en memoria para guardar y consultar usuarios. No son mocks: son implementaciones concretas no durables.

### Entrypoints

Incluyen la interfaz CLI y el controlador que transforma las entradas del usuario en comandos de aplicación.

## Decisiones pedagógicas

- Las dependencias se ensamblan manualmente en `Main`.
- No se utiliza Spring ni un contenedor de inyección de dependencias.
- No se utiliza una base de datos.
- No se utiliza ORM.
- No se utiliza caché.
- La persistencia en memoria permite observar con claridad el flujo entre capas.

## Requisitos

- Java 17 o superior.
- Maven 3.9 o superior.

## Ejecutar pruebas

```bash
mvn clean install
```

## Revisar cobertura

El build genera el reporte JaCoCo en:

```text
target/site/jacoco/index.html
```

Para revisar la cobertura diferencial respecto a `main`:

```bash
diff-cover target/site/jacoco/jacoco.xml --compare-branch=main
```
Debes instalar **diff-cover** que es una utilidad desarrollando sobre Python.

## Ejecutar la aplicación

```bash
mvn org.codehaus.mojo:exec-maven-plugin:3.5.0:java -Dexec.mainClass=com.jcaa.udec.Main
```
