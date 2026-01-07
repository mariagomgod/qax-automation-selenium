# Portafolio de Automatización de Web con Selenium WebDriver

> ¡Hola! Soy María, una apasionada QA en formación, enfocada en la automatización de Web con Selenium WebDriver + Serenity BDD
> Este repositorio contiene los proyectos que he desarrollado como parte de la mentoría QA Pro Level de QAX.
>
### 🚀 Tecnologías que utilizo
> - **Lenguaje:** Java 11, 17 ó superior
> - **Framework de Automatización:** Selenium WebDriver, Serenity BDD
> - **Gestión de Pruebas:** TestNG, JUnit5
> - **Herramientas Adicionales:** Git, Maven, Cucumber
> 
### 🎯 Objetivos:
> 
> - Instalar y configurar JDK y Intellij IDEA para desarrollo en Java. 
> - Crear un proyecto Maven desde cero e integrar las dependencias necesarias.
> - Ejecutar tests de conexión con Selenium WebDriver para validar el entorno.
> - Comprender y aplicar localizadores robustos (CSS y XPath) en la automatización web.
> - Saber utilizar Serenity BDD + Patrón Screenplay
>
### 📚 Proyectos
>
> #### Stage 1: Fundamentos de API Testing con Rest Assured
> - **Challenge:** Primeros Pasos con Selenium Webdriver y localizadores web.
>   - Link a la carpeta del Challenge: `https://github.com/mariagomgod/qax-automation-selenium/tree/main/Stage_1/Challenge`
> - **Mission:** Automatización real.
>   - Link a la carpeta de la Mission: `https://github.com/mariagomgod/qax-automation-selenium/tree/main/Stage_1/Mission`
> - **Quick Task:** Configurar el entorno y conocer los localizadores web.
>   - Link a la carpeta de Quick Task: `https://github.com/mariagomgod/qax-automation-selenium/tree/main/Stage_1/Quick_Task`
>
> #### Stage 2: Flujos completos de automatización utilizando distintas peticiones HTTP como GET y POST
> - **Challenge:** Dominio del Navegador con Selenium WebDriver.
>   - Link a la carpeta del Challenge: `https://github.com/mariagomgod/qax-automation-selenium/tree/main/Stage_2/Challenge`
> - **Mission:** Automatización de un E2E con comportamientos avanzados.
>   - Link a la carpeta de la Mission: `https://github.com/mariagomgod/qax-automation-selenium/tree/main/Stage_2/Mission`
> - **Quick Task:** Desarrollo de destrezas en la localización y acciones avanzadas con Selenium WebDriver.
>   - Link a la carpeta de Quick Task: `https://github.com/mariagomgod/qax-automation-selenium/tree/main/Stage_2/Quick_Task/WarmUp`
>
> #### Stage 3 (Work In Progress): Screenplay Pattern + Serenity BDD
> - **Challenge:** ¿?
    >   - Link a la carpeta del Challenge: ``
> - **Mission:** ¿?
    >   - Link a la carpeta de la Mission: ``
> - **Quick Task:** Preparando el Entorno para un Framework Profesional, utilizando Serenity BDD y Cucumber (BDD)
    >   - Link a la carpeta de Quick Task: `https://github.com/mariagomgod/qax-automation-selenium/tree/main/Stage_3/Quick_Task/WarmUp`
>
## 🧱 2. Estructura del Repositorio (Work In Progress)
```
.
├── .gitignore   <-- Archivo para ignorar archivos que no deben subirse.
├── README.md    <-- Readme del proyecto
├── assets/      <-- Carpeta para guardar imágenes o recursos que uses en tu documentación.
├── Stage_1/     <-- Carpeta para todo el trabajo del Módulo 1.
│   ├── Challenge/
│   │   └── challenge_1
│   │       └── qa-automation-challenge1
│   │           └── casos_de_prueba.md
│   │           └── README.md
│   ├── Mission/
│   │   └── mission_casos_de_prueba
│   │       └── qa-automation-casos-de-prueba
│   │           └── casos_de_prueba.md
│   │           └── README.md
│   └── Quick_Task/
│   │   └── quick_task
│   │       └── qa-automation
│   │           └── caso_de_prueba_amazon-selectors.md
│   │           └── README.md
└── Stage_2/     <-- Carpeta para todo el trabajo del Módulo 2.
│   ├── Challenge/
│   │   │   └── challenge_1
│   │   │       └── Challenge1
│   │   │            └── qa-automation
│   │   │               └──casos_de_prueba.md
│   │   │               └──README.md
│   │   │   └── challenge_2
│   │   │       └── Challenge2
│   │   │            └── qa-automation
│   │   │               └──casos_de_prueba.md
│   │   │               └──README.md
│   ├── Mission/
│   │   │   └── mission
│   │   │       └── Mission
│   │   │            └── qa-automation
│   │   │            └── casos_de_prueba.md
│   │   │            └──README.md
│   └── Quick_Task/
│   │   └── WarmUp
│   │   │   └── qa-automation
│   │   │       └── locators-compra.md
│   │   │       └── locators_shadow-iframe-popup.md
│   │   │       └── README.md
└── Stage_3/     <-- Carpeta para todo el trabajo del Módulo 3.
│   └── Quick_Task/
│   │   └── WarmUp
│   │       └── qa-automation-screenplay
│   │       │   └── README.md
```

### 🧪 Cómo ejecutar las pruebas (Selenium WebDriver) - Stages 1 y 2:
> 1. Clonar el repositorio de Git `https://github.com/mariagomgod/qax-automation-selenium`
> 2. Navegar a la carpeta (ej: `Stage_1/Challenge/challenge_1`) 
> 3. Ejecutar el comando `mvn clean test` desde la terminal. 
> 4. El reporte de resultados se generará en la carpeta `target/surefire-reports`.
>
### 🧪 Cómo ejecutar las pruebas (Screenplay Pattern + Serenity BDD) - Stage 3 - WORK IN PROGRESS:
> 1. Clonar el repositorio de Git `https://github.com/mariagomgod/qax-automation-selenium`
> 2. Navegar a la carpeta (ej: `Stage_3/Challenge/challenge_1`)
> 3. Comandos a ejecutar:
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
      chrome {
      headless = true
      }
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
> 4. El reporte de resultados se generará en la carpeta `target/surefire-reports`.
>
### 📝 Contacto: 
> - **GitHub:** `https://github.com/mariagomgod/`
> - **LinkedIn:** `https://www.linkedin.com/in/maria-gomez-godoy/`
> - **Email:** `mariagomezgodoy2@gmail.com`