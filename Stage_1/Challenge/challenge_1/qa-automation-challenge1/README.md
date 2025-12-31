## Challenge 01: Abriendo la aplicación y validando el título de la página de registro de Parabank y hacer distintas navegaciones en la web de Amazon

### 🎯 Objetivos:

- Familiarización con Selenium WebDriver, aprendiendo a ejecutar los primeros scripts de automatización.
- Utilización de comandos básicos.
- Cómo mapear elementos dentro del DOM de una página web.

---

## ⚙ Requerimientos

- Java 17 o superior
- Maven
- IDE (IntelliJ IDEA o VS Code)
- WebDriverManager
- JUnit5
- Cucumber
- Selenium 4.x
- Conexión a Internet

---

## 🧪 Cómo he realizado los tests de lo ejercicios planteados a continuación:
- Me he basado en el **patrón de diseño Page Object Model (POM)** que separa los tests de la UI: cada página se modela como una clase con sus **selectores** y **acciones**.
  **Resultado:** tests más **legibles**, **reutilizables** y **fáciles de mantener** ante cambios en la interfaz.
- He organizado el proyecto en varias carpetas:
    - **Config:** configuración de las páginas web utilizadas en los distintos ejercicios.
    - **Pages:** contiene las clases que representan cada página, con sus selectores y acciones.
    - **Runners:** configuración del runner para ejecutar los tests.
    - **Steps:** una clase por cada ejercicio, donde se definen los pasos de cada test.
    - **Utils:** incluye el **RunContext**, que sirve para compartir estado y dependencias entre los steps.
    - *NOTA:* he ido documentando aquellas líneas de código que he creido más difíciles de entender o su propósito en los tests, con un comentario encima de cada una de ellas.

---

## ⚙ ¿Cómo ejecutar los tests?
Con el comando `mvn clean test` desde la terminal.

---
## 📝 Ejercicios

### Ejercicio 1: Parabank

1. **Abrir la aplicación Parabank: https://parabank.parasoft.com/parabank/index.htm**
2. **Hacer clic en "Register":**
3. **Obtener el texto del `<h1>` en la página de registro:**
    - Imprimir el texto en consola:
      ```Página de Registro cargada. Encabezado encontrado: Signing up is easy!```
4. **Ingresar los datos solicitados en el formulario.**
5. **Hacer Login.**
6. **Cerrar el navegador.**

---

### Ejercicio 02: Navegación de ofertas y categorías en Amazon

1. **Abrir la aplicación Amazon: https://www.amazon.com/**
2. **Hacer distintas navegaciones por Amazon**
3. **Filtrar productos y llegar a “Compra juguetes más vendidos”**
4. **Cerrar el navegador.**