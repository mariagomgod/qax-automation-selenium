# QAXpert – Automation Web (Serenity BDD + Screenplay)

Proyecto base de automatización web usando **Java**, **Serenity BDD** y el **patrón Screenplay**.  
Este proyecto está pensado como punto de inicio para prácticas, entrenamientos y mentorías en QAXpert.

---

## Tecnologías usadas

- Java 11
- Maven
- Serenity BDD
- Screenplay Pattern
- JUnit

---

## Comandos

```bash
mvn clean
mvn compile
mvn clean verify
mvn clean verify -Dcucumber.filter.tags="@smoke"
mvn clean verify -Dcucumber.filter.tags="@negativo"
mvn clean verify -Dcucumber.filter.tags="@registro"

```