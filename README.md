# JPA Features

##  Voraussetzungen
- PostgreSQL oder Docker installiert
- Java 8 installiert
- Maven
- IntelliJ

Hibernate implementiert Standard JPA 
EclipseLink -||-

Spring Data JPA setzt auf JPA
Spring Data JDBC

## Themen: In Richtung Spring Data JPA
    - Object Lifecycle (Thorben Janssen) JPA Entity Lifecycle Model (https://www.youtube.com/watch?v=Y7PpjerZkc0)
    - 10 Common Hibernate Problems that cripple your performance (https://thorben-janssen.com/common-hibernate-mistakes-cripple-performance/)
    - Repository Arten (Repository, CRUD, Pagination/Sorting) @RepositoryDefinition/@NoRepositoryBean e.g. readonly
    - @Entity/@Document
    - Cascading/Lazy und Eager Loading
    - Transactional - Einschalten der SQL Statements (Pitfalls)
    - save() vs saveAndFlush()
    - Query Methods
    - Pagination
    - Generic Tables/Objects
    - Projections
    - DB Trigger
    - Testing - h2 DB
    - Microservice Patterns
    - @SQL für Integrationstests

--> Ebene des Pitfalls: Spring, Spring Data, JPA, DB
https://codete.com/blog/5-common-spring-transactional-pitfalls/

--> Video der JUG: Drei Patterns für skalierbare Microservices

--> Aufbau bei einer Code Sequenz
Zeit Messung

Beispiel:
Docker
Feature Toggle: https://www.baeldung.com/spring-feature-flags
Cleanup Script - eventuell flyway?

## Object Lifecycle



## Feature 1: Readonly Repository
Bei der Verwendung einer strikten Architektur, bei der nicht aus versehen Daten aus der Datenbank verändert werden sollten, empfiehlt es sich auf ein Readonly-Repository zurückzugreifen.

**ACHTUNG:** Sollte ein @Transactional verwendet werden, dann muss die Transaktion als readonly gekennzeichnet werden. 

```
@Transactional(readOnly = true)
```

## Feature 2: @Transactional (und Pitfalls)




## Feature 3: Projections (Kommunikation mit dem UI) 


## Feature 4: Mappings (OneToMany, OneToOne, ManyToOne)


## Feature 5: @SQL (Spannendes Testen)
