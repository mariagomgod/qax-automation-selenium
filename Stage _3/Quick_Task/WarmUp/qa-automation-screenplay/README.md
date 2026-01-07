# Quick Task: Preparando el Entorno para un Framework Profesional

## 🎯 Objetivo
Este Quick Task tiene como objetivo **comprender el patrón Screenplay** y cómo se utiliza para **modelar flujos de automatización web** de forma clara y reutilizable.
El ejercicio se apoya en el contexto de **Serenity BDD + Cucumber (BDD)** y Selenium WebDriver, **sin implementar código real**, sino describiendo los flujos mediante **lenguaje natural estructurado (pseudocódigo)**, tal como se diseñaría un framework profesional.

---

## ⚙ Requerimientos

- Java 11 ó 17
- Maven
- IDE (IntelliJ IDEA o VS Code)
- WebDriverManager
- Selenium 4.x
- Serenity BDD
- Cucumber
- Conexión a Internet

> ⚠️ Nota: No es necesario ejecutar código. Los requerimientos representan un **contexto real de proyecto**.

---
## 📂 Instrucciones

1. Debes describir cómo se vería el flujo usando Screenplay, usando lenguaje natural estructurado, no código Java.
- 📝 Pseudocódigo esperado (ejemplo):
  ```
  Actor: Usuario
  Habilidad: Navegar por la aplicación web
  ```
---
## 📝 Notas importantes

- **NOTA 1:** El ejercicio ya se encuentra resuelto en el archivo `casos_de_prueba.md`.
- **NOTA 2:** El proyecto **no contiene código ejecutable**.  
  Su objetivo es mostrar **cómo se estructuraría un framework** usando Screenplay + Serenity BDD.

---

## Comandos de ejecución (Referencia): 

> ⚠️ Nota: Estos comandos se incluyen únicamente como **referencia educativa**, para mostrar cómo se ejecutaría un proyecto real con Serenity BDD.

- Ejecutar toda la suite de tests y genera los reportes de Serenity utilizando el comando Maven (Chrome):
    ```bash
    mvn clean verify
    ```
  - **¿Dónde encontramos el reporte generado?**
    - Después de ejecutar:
    ```
    target/site/serenity/index.html
    ```
- Ejecutar toda la suite de tests sin generar reportes utilizando el comando Maven (Chrome):
  ```bash
  mvn clean test
  ```
- Ejecutar un feature específico utilizando el comando Maven (Chrome):
  ```bash
  mvn clean verify -Dcucumber.features=src/test/resources/features/registro.feature
  ```
- Ejecutar un escenario por tag:
  - En tu feature:
    ```
    @smoke
    Scenario: Registro exitoso
    ```
  - Ejecutar solo ese tag:
  ```bash
  mvn clean verify -Dcucumber.filter.tags="@smoke"
  ```
- Ejecutar en modo Headless:
    - Si ya está configurado en serenity.conf:
      ```
      headless.mode = true
      ```
    - Ejecutar con el comando:
  ```bash
  mvn clean verify -Dheadless=true
  ```
- Ejecutar con navegador específico utilizando el comando Maven (Chrome):
  ```bash
  mvn clean verify -Dwebdriver.driver=chrome
  ```
- Ejecutar una clase Runner utilizando el comando Maven (Chrome):
  ```bash
  mvn clean verify -Dtest=RunCucumberTest
  ```