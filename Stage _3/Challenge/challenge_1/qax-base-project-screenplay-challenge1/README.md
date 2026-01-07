# Challenge 1 – Automation Web (Serenity BDD + Screenplay) 
Proyecto base de automatización web usando **Java**, **Serenity BDD** y el **patrón Screenplay**.  

## 🎯 Objetivo
- Aumentar la cobertura de pruebas automatizadas partiendo del proyecto actual, aplicando correctamente el patrón Screenplay, buenas prácticas de diseño y una mentalidad orientada al valor.
---

## ⚙ Tecnologías usadas

- Java 11
- Maven
- Serenity BDD
- Screenplay Pattern
- JUnit
- Cucumber

---

## Comandos de ejecución:

```bash
mvn clean
mvn compile
```
- Ejecutar toda la suite de tests y generar los reportes de Serenity utilizando el comando Maven (Chrome):
    ```bash
    mvn clean verify
    ```
  - **¿Dónde encontramos el reporte generado?**
    - Después de ejecutar:
    ```
    Lo encontramos en: target/site/serenity/index.html
    
    Para abrir el reporte: Botón derecho del ratón sobre el archivo index.hmtl -> Open In -> Browser -> ej: Chrome
    ```
- Ejecutar un escenario por tag:
    ```bash
    mvn clean verify -Dcucumber.filter.tags="@smoke"
    mvn clean verify -Dcucumber.filter.tags="@negativo"
    mvn clean verify -Dcucumber.filter.tags="@registro" 
    ```